package com.leave.www.service;



import com.leave.www.dtos.LeaveActionDTO;
import com.leave.www.dtos.LeaveRequestDTO;
import com.leave.www.dtos.LeaveResponseDTO;

import java.util.List;

public interface LeaveService {

    void applyLeave(LeaveRequestDTO request);

    void approveLeave(Long leaveId, LeaveActionDTO actionDTO);

    void rejectLeave(Long leaveId, LeaveActionDTO actionDTO);

    List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId);
}
