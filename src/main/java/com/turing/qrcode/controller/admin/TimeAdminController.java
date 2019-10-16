package com.turing.qrcode.controller.admin;

import com.turing.qrcode.controller.service.TimeService;
import com.turing.qrcode.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Meng
 * @date 2019/10/16
 */
@Controller
@RequestMapping("/admin")
@ResponseBody
@Api(tags = "后台管理签到签退记录接口")
public class TimeAdminController {
    @Autowired
    private TimeService timeService;

    @ApiOperation(value = "返回全部人的签到签退记录情况，时间降序,state0为签到，state1为签退", notes = "state", httpMethod = "GET")
    @GetMapping("/time")
    public Message selectTimes(){
        return Message.success().add("times",timeService.selectTimes());
    }

    @ApiOperation(value = "返回某个学生的签到签退记录情况，时间降序", notes = "state", httpMethod = "GET")
    @ApiImplicitParam(name = "studentId",required = true,dataType = "GET",paramType = "int")
    @GetMapping("/time/{studentId}")
    public Message selectTime(@PathVariable("studentId")Integer studentId){
        return Message.success().add("times",timeService.selectTimeBystudentId(studentId));
    }
}
