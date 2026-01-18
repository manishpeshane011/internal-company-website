package com.leave.www.dtos;

import lombok.Data;

@Data
public class LeaveActionDTO {
    private Long approverId;
    private String comments;

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
