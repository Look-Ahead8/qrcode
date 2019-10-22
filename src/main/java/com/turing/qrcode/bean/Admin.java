package com.turing.qrcode.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Admin {
    @ApiModelProperty(hidden = true)
    private Integer adminId;

    @NotBlank(message = "用户名不可以为空")
    private String adminName;

    @ApiModelProperty(hidden = true)
    private String adminStudentNo;

    @Size(max = 16, min = 6, message = "请输入6-16位正确的密码")
    @JsonIgnore
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