package com.aihrms.backend.leave.service;

import com.aihrms.backend.leave.entity.Leave;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LeaveService {

    Leave saveLeave(Leave leave);

    Page<Leave> getAllLeaves(int page, int size);

    Leave getLeaveById(Long id);

    Leave updateLeave(Long id, Leave leave);

    void deleteLeave(Long id);

    List<Leave> searchLeaves(String keyword);

    Leave approveLeave(Long id);

    Leave rejectLeave(Long id);
}