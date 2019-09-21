package com.turing.qrcode.controller.service.impl;

import com.turing.qrcode.bean.Table;
import com.turing.qrcode.controller.service.TableService;
import com.turing.qrcode.dao.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Meng
 * @date 2019/9/20
 */
@Service
@Transactional
public class TableServiceImpl implements TableService {
    @Autowired
    private TableMapper tableMapper;

    /**
     * 返回桌子是否已经被人坐下了
     * @param tableId
     * @return 已坐下返回true，否则为false
     */
    @Override
    public boolean getTableIsSit(Integer tableId) {
        Table table = tableMapper.selectByPrimaryKey(tableId);
        return table.getState();
    }
}
