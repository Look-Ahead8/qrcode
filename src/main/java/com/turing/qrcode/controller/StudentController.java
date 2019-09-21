package com.turing.qrcode.controller;

import com.turing.qrcode.bean.Student;
import com.turing.qrcode.controller.service.StudentService;
import com.turing.qrcode.controller.service.TableService;
import com.turing.qrcode.message.Message;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Meng
 * @date 2019/9/18
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    @ApiOperation(value = "登录坐下桌子开始学习",notes = "根据学生姓名登录，密码默认为123456.姓名非空，密码6-16位.登录成功会返回该学生信息.",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="studentName",value = "学生姓名",dataType = "string",paramType = "query",required = true),
            @ApiImplicitParam(name="studentPassword",value = "学生密码",dataType = "string",paramType = "query",required = true),
            @ApiImplicitParam(name="tableId",value = "桌子id",dataType = "int",paramType = "query",required = true),
            @ApiImplicitParam(name="studentId",value = "学生id(不用)",dataType = "int",paramType = "query",readOnly = true),
            @ApiImplicitParam(name="studentNo",value = "学生学号(不用)",dataType = "string",paramType = "query",readOnly = true),
            @ApiImplicitParam(name="studentTime",value = "学生总学习时间(不用)/秒",dataType = "int",paramType = "query",readOnly = true)
    })
    private Message login(@Valid Student student, BindingResult result, @RequestParam("tableId") Integer tableId) {
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.fail().add("errors", map);
        }
        int processResult = studentService.login(student, tableId);
        if (processResult == 1) {
            map.put("message","用户名不存在或密码错误");
            return Message.fail().add("errors",map);
        }
        else if(processResult==2){
            map.put("message","请输入正确的桌子号码");
            return Message.fail().add("errors",map);
        }
        else if(processResult==4){
            map.put("message","请先在你原来椅子上退出在登录");
            return Message.fail().add("errors",map);
        }
        else if(processResult==3){
            map.put("message","该桌子已经被人坐下");
            return Message.fail().add("errors",map);
        }
        Student loginStudent=studentService.selectByStudentName(student.getStudentName());
        return Message.success().add("student",loginStudent);
    }

    @PostMapping("/loginout")
    @ApiOperation(value = "退出桌子",notes = "在该学生已在该桌子学习的情况下，退出该桌子，并获得学习时长",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tableId",value = "桌子id",dataType = "int",paramType = "query",required = true)
    })
    public Message loginout(@RequestParam("tableId") Integer tableId){
        Map<String,String> map=new HashMap();
        int result=studentService.loginout(tableId);
        if (result == 1) {
            map.put("message","请输入正确的桌子号码");
            return Message.fail().add("errors",map);
        }
//        else if (result == 2) {
//            map.put("message","请输入正确的学生号码");
//            return Message.fail().add("errors",map);
//        }
        else if (result == 3) {
            map.put("message","该桌子还没有人坐下，请先签到");
            return Message.fail().add("errors",map);
        }
//        else if (result == 4) {
//            map.put("message","该桌子已经有别人坐下了,请换一张桌子签到");
//            return Message.fail().add("errors",map);
//        }
        return Message.success();
    }

}
