package com.turing.qrcode.controller.service;

import com.turing.qrcode.bean.Table;

import java.util.List;

/**
 * @author Meng
 * @date 2019/9/20
 */
public interface TableService {
    boolean getTableIsSit(Integer tableId);

    List<Table> getAllTable();
}
