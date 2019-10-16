package com.turing.qrcode.controller.service;

import com.turing.qrcode.bean.Time;

import java.util.List;

/**
 * @author Meng
 * @date 2019/10/16
 */
public interface TimeService {
    List<Time> selectTimes();

    List<Time> selectTimeBystudentId(Integer studentId);
}
