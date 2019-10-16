package com.turing.qrcode.bean;

import io.swagger.annotations.ApiModelProperty;

public class Admin {
    @ApiModelProperty(hidden = true)
    private Integer adminId;

    private String adminName;

    @ApiModelProperty(hidden = true)
    private String adminStudentNo;

    private String adminPassword;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminStudentNo() {
        return adminStudentNo;
    }

    public void setAdminStudentNo(String adminStudentNo) {
        this.adminStudentNo = adminStudentNo == null ? null : adminStudentNo.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }
}