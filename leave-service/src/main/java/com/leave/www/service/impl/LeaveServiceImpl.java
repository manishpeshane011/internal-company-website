package com.leave.www.service.impl;


import com.common.www.exception.InvalidLeaveStateException;
import com.common.www.exception.LeaveNotFoundException;
import com.leave.www.dtos.LeaveActionDTO;
import com.leave.www.dtos.LeaveRequestDTO;
import com.leave.www.dtos.LeaveResponseDTO;
import com.leave.www.enums.LeaveStatus;
import com.leave.www.model.Leave;
import com.leave.www.repository.LeaveRepository;
import com.leave.www.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public void applyLeave(LeaveRequestDTO request) {
        //log.info("Applying leave for employeeId={}", request.getEmployeeId());

        Leave leave = new Leave();
        leave.setLeaveType(request.getLeaveType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());
        leave.setStatus(LeaveStatus.PENDING);

        leaveRepository.save(leave);

        //log.info("Leave successfully applied");
    }

    @Override
    public void approveLeave(Long leaveId, LeaveActionDTO actionDTO) {
        Leave leave = getLeaveOrThrow(leaveId);

        validatePending(leave);

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setComments(actionDTO.getComments());

        leaveRepository.save(leave);
        //log.info("Leave approved id={}", leaveId);
    }

    @Override
    public void rejectLeave(Long leaveId, LeaveActionDTO actionDTO) {
        Leave leave = getLeaveOrThrow(leaveId);

        validatePending(leave);

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setComments(actionDTO.getComments());

        leaveRepository.save(leave);
        //log.info("Leave rejected id={}", leaveId);
    }

    @Override
    public List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private Leave getLeaveOrThrow(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException("Leave not found with id " + id));
    }

    private void validatePending(Leave leave) {
        if (!"PENDING".equalsIgnoreCase(leave.getStatus().toString())) {
            throw new InvalidLeaveStateException("Leave already processed");
        }
    }

    private LeaveResponseDTO mapToDTO(Leave leave) {

        LeaveResponseDTO dto = new LeaveResponseDTO();

        dto.setLeaveId(leave.getEmployeeId());
        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setDuration(leave.getDuration());
        dto.setStatus(leave.getStatus().toString());
        dto.setComments(leave.getComments());

        return dto;
    }

}
