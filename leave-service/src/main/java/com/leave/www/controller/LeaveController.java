package com.leave.www.controller;

import com.leave.www.dtos.LeaveActionDTO;
import com.leave.www.dtos.LeaveRequestDTO;
import com.leave.www.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
//@RequiredArgsConstructor
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequestDTO request) {
        leaveService.applyLeave(request);
        return ResponseEntity.ok("Leave applied");
    }

    @PostMapping("/{id}/approve")
   // @PreAuthorize("hasAnyRole('HR','ADMIN')")
    public ResponseEntity<?> approve(
            @PathVariable Long id,
            @RequestBody LeaveActionDTO actionDTO) {
        leaveService.approveLeave(id, actionDTO);
        return ResponseEntity.ok("Leave approved");
    }

    @PostMapping("/{id}/reject")
   // @PreAuthorize("hasAnyRole('HR','ADMIN')")
    public ResponseEntity<?> reject(
            @PathVariable Long id,
            @RequestBody LeaveActionDTO actionDTO) {
        leaveService.rejectLeave(id, actionDTO);
        return ResponseEntity.ok("Leave rejected");
    }
}
