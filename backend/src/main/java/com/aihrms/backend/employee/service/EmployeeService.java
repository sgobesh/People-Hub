package com.aihrms.backend.employee.service;

import com.aihrms.backend.department.entity.Department;
import com.aihrms.backend.department.repository.DepartmentRepository;
import com.aihrms.backend.employee.dto.EmployeeRequestDTO;
import com.aihrms.backend.employee.dto.EmployeeResponseDTO;
import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.mapper.EmployeeMapper;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import com.aihrms.backend.notification.service.NotificationService;
import com.aihrms.backend.notification.enums.NotificationType;
import java.util.List;
import java.util.stream.Collectors;
import com.aihrms.backend.leave.repository.LeaveRepository;
import com.aihrms.backend.attendance.repository.AttendanceRepository;
import com.aihrms.backend.payroll.repository.PayrollRepository;
import com.aihrms.backend.performance.repository.PerformanceRepository;
import com.aihrms.backend.security.entity.Role;
import com.aihrms.backend.security.entity.User;
import com.aihrms.backend.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class EmployeeService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

private final NotificationService notificationService;
private final AttendanceRepository attendanceRepository;
private final LeaveRepository leaveRepository;
private final PayrollRepository payrollRepository;
private final PerformanceRepository performanceRepository;
   public EmployeeService(
        EmployeeRepository employeeRepository,
        DepartmentRepository departmentRepository,
        NotificationService notificationService,
        LeaveRepository leaveRepository,
        AttendanceRepository attendanceRepository,
        PayrollRepository payrollRepository,
        PerformanceRepository performanceRepository,
        UserRepository userRepository,
PasswordEncoder passwordEncoder
) {

    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
    this.notificationService = notificationService;
this.userRepository = userRepository;
this.passwordEncoder = passwordEncoder;
    this.leaveRepository = leaveRepository;
    this.attendanceRepository = attendanceRepository;
    this.payrollRepository = payrollRepository;
    this.performanceRepository = performanceRepository;
}




    // Create Employee

  public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

    Employee employee = EmployeeMapper.toEntity(dto);

if (dto.getDepartmentId() != null) {

    Department department = departmentRepository.findById(dto.getDepartmentId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Department not found with id: "
                                    + dto.getDepartmentId()));

    employee.setDepartment(department);
}

// Generate Employee ID BEFORE saving
Long maxId = employeeRepository.findMaxId();

long nextId = (maxId == null) ? 1 : maxId + 1;

employee.setEmployeeId(
        "EMP" + String.format("%04d", nextId)
);

Employee savedEmployee = employeeRepository.save(employee);
    // Create Login Account Automatically
    // Create Login Account Automatically
if (!userRepository.existsByEmail(savedEmployee.getEmail())) {

    User user = new User();

    user.setName(
            savedEmployee.getFirstName() + " " +
            savedEmployee.getLastName());

    user.setEmail(savedEmployee.getEmail());

    user.setPassword(
            passwordEncoder.encode("Welcome@123"));

    user.setRole(Role.EMPLOYEE);

    user.setEmployee(savedEmployee);

    userRepository.save(user);
}

    notificationService.createNotification(
            "New Employee Added",
            savedEmployee.getFirstName() + " " +
                    savedEmployee.getLastName() +
                    " joined the organization.",
            NotificationType.EMPLOYEE
    );

    return EmployeeMapper.toResponseDTO(savedEmployee);
}





    // Get All Employees

    public List<EmployeeResponseDTO> getAllEmployees(){


        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());

    }


public List<EmployeeResponseDTO> searchEmployees(String keyword) {

    return employeeRepository
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmployeeIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    keyword,
                    keyword,
                    keyword,
                    keyword
            )
            .stream()
            .map(EmployeeMapper::toResponseDTO)
            .toList();
}
public Page<EmployeeResponseDTO> getEmployeesWithPagination(
        int page,
        int size,
        String sortBy,
        String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return employeeRepository.findAll(pageable)
            .map(EmployeeMapper::toResponseDTO);
}


    // Get Employee By ID

    public EmployeeResponseDTO getEmployeeById(Long id){


        Employee employee =
                employeeRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Employee not found with id: " + id
                ));


        return EmployeeMapper.toResponseDTO(employee);

    }







    // Update Employee

  public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Employee not found with id: " + id));

   
    employee.setFirstName(dto.getFirstName());
    employee.setLastName(dto.getLastName());
    employee.setEmail(dto.getEmail());
    employee.setPhone(dto.getPhone());
    employee.setDesignation(dto.getDesignation());
    employee.setSalary(dto.getSalary());
    employee.setJoiningDate(dto.getJoiningDate());
    employee.setGender(dto.getGender());

    if (dto.getDepartmentId() != null) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: "
                                        + dto.getDepartmentId()));

        employee.setDepartment(department);
    }

    Employee updatedEmployee = employeeRepository.save(employee);

    // Update User details
    userRepository.findByEmployee(updatedEmployee).ifPresent(user -> {

        user.setName(updatedEmployee.getFirstName() + " " +
                updatedEmployee.getLastName());

        user.setEmail(updatedEmployee.getEmail());

        userRepository.save(user);
    });

    return EmployeeMapper.toResponseDTO(updatedEmployee);
}




    // Delete Employee

   public void deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Employee not found with id: " + id));

    if (attendanceRepository.existsByEmployeeId(id)) {
        throw new RuntimeException(
                "Cannot delete employee because attendance records exist.");
    }

    if (leaveRepository.existsByEmployeeId(id)) {
        throw new RuntimeException(
                "Cannot delete employee because leave records exist.");
    }

    if (payrollRepository.existsByEmployeeId(id)) {
        throw new RuntimeException(
                "Cannot delete employee because payroll records exist.");
    }

    if (performanceRepository.existsByEmployeeId(id)) {
        throw new RuntimeException(
                "Cannot delete employee because performance records exist.");
    }

    // Delete linked login account
    userRepository.findByEmployee(employee)
            .ifPresent(userRepository::delete);

    employeeRepository.delete(employee);
}

}