package com.turing.qrcode.controller.service.impl;

import com.turing.qrcode.bean.Time;
import com.turing.qrcode.bean.TimeExample;
import com.turing.qrcode.controller.service.TimeService;
import com.turing.qrcode.dao.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Meng
 * @date 2019/10/16
 */
@Service
@Transactional
public class TimeServiceImpl implements TimeService {
    @Autowired
    private TimeMapper timeMapper;

    @Override
    public List<Time> selectTimes() {
        return timeMapper.selectTimes();
    }
}
