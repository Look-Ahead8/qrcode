package com.turing.qrcode.controller.service;

import com.turing.qrcode.bean.Student;

import java.util.List;

/**
 * @author Meng
 * @date 2019/9/18
 */
public interface StudentService {
    int login(Student student,int tableId);

    int loginout(Integer tableId);

    Student selectByStudentName(String studentName);

    List<Student> getAllStudentOrderByTime();

    void autoLoginOut(String[] macs);
}
