package com.turing.qrcode.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Student {
    @ApiModelProperty(hidden = true)
    private Integer studentId;

    @NotBlank(message = "姓名不可以为空")
    private String studentName;

    @ApiModelProperty(hidden = true)
    private String studentNo;

    @ApiModelProperty(hidden = true)
    private Integer studentTime;

    @JsonIgnore
    @Size(max = 16, min = 6, message = "请输入6-16位正确的密码")
    private String studentPassword;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public Integer getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(Integer studentTime) {
        this.studentTime = studentTime;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword == null ? null : studentPassword.trim();
    }
}