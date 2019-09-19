package com.turing.qrcode.controller.service;

import com.turing.qrcode.bean.Student;

/**
 * @author Meng
 * @date 2019/9/18
 */
public interface StudentService {
    int login(Student student,int tableId);

    int loginout(Integer tableId,Integer studentId);

    Student selectByStudentName(String studentName);
}
