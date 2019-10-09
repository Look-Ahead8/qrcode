package com.turing.qrcode.controller;

import com.turing.qrcode.bean.Table;
import com.turing.qrcode.controller.service.TableService;
import com.turing.qrcode.message.Message;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Meng
 * @date 2019/9/20
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TableController {
    @Autowired
    private TableService tableService;


    @ApiOperation(value = "查看桌子是否已被坐下", notes = "桌子没人坐下返回状态码100，有人坐下返回200，注意桌子id拼接在url后面", httpMethod = "GET")
    @ApiImplicitParam(name = "tableId", value = "桌子id", dataType = "int", required = true, paramType = "path")
    @GetMapping("/table/{tableId}")
    public Message getTableIsSit(@PathVariable("tableId") Integer tableId) {
        boolean isSit = tableService.getTableIsSit(tableId);
        if (isSit) return Message.fail().add("message", "该桌子已经被人坐下");
        return Message.success().add("message", "该桌子还没人坐下");
    }

    @ApiOperation(value = "返回全部桌子的情况",notes="state为false为还没人坐下，否则为已经有人坐下",httpMethod = "GET")
    @GetMapping("/table")
    public Message getAllTable() {
        List<Table> tables = tableService.getAllTable();
        return Message.success().add("tables", tables);
    }
}
