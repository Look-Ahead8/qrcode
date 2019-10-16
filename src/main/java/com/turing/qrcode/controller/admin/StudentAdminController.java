package com.turing.qrcode.controller.admin;

import com.turing.qrcode.bean.Student;
import com.turing.qrcode.controller.service.StudentService;
import com.turing.qrcode.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Meng
 * @date 2019/10/11
 */
@Controller()
@RequestMapping("/admin")
@Api(tags = "后台管理学生后台管理接口")
public class StudentAdminController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "返回全部学生", notes = "返回全部学生", httpMethod = "GET")
    public Message getAllStudent() {
        List<Student> list = studentService.getAllStudentOrderByTime();
        return Message.success().add("students", list);
    }
}
