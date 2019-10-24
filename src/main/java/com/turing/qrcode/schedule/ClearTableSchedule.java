package com.turing.qrcode.schedule;

import com.turing.qrcode.bean.Table;
import com.turing.qrcode.controller.service.StudentService;
import com.turing.qrcode.controller.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Meng
 * @date 2019/10/24
 */
@Component
@EnableScheduling
public class ClearTableSchedule {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TableService tableService;
    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "0 0 0 * * *")
    private void clearTable(){
        logger.info("开始每天清除");
        List<Table> allTable = tableService.getAllTable();
        for (Table table:allTable){
            if(table.getState()){
                logger.info(table.getTableId()+"号桌子被强制退出");
                studentService.loginout(table.getTableId());
            }
        }
        logger.info("清除完毕");
    }
}
