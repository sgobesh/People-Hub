package com.aihrms.backend.settings.service;

import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.settings.dto.CompanyRequestDTO;
import com.aihrms.backend.settings.dto.CompanyResponseDTO;
import com.aihrms.backend.settings.entity.Company;
import com.aihrms.backend.settings.mapper.CompanyMapper;
import com.aihrms.backend.settings.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    private final CompanyRepository companyRepository;

    public SettingsService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Get Company Details
    public CompanyResponseDTO getCompany() {

        Company company = companyRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        if (company == null) {
            return null;
        }

        return CompanyMapper.toDTO(company);
    }

    // Save or Update Company
    public CompanyResponseDTO saveCompany(CompanyRequestDTO dto) {

        Company company = companyRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new Company());

        CompanyMapper.update(company, dto);

        Company saved = companyRepository.save(company);

        return CompanyMapper.toDTO(saved);
    }

    // Get by ID (optional)
    public CompanyResponseDTO getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id : " + id));

        return CompanyMapper.toDTO(company);
    }
}