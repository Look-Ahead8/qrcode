package com.turing.qrcode.controller.admin;

import com.turing.qrcode.bean.Admin;
import com.turing.qrcode.controller.service.AdminService;
import com.turing.qrcode.message.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Meng
 * @date 2019/10/16
 */
@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/admin")
@ResponseBody
@Api(tags = "管理员接口")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录接口", notes = "管理员登录接口，密码默认为123456，用户名为姓名", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName", value = "用户名，姓名", dataType = "string", required = true, paramType = "path"),
            @ApiImplicitParam(name = "adminPassword", value = "密码(6-16位)", dataType = "string", required = true, paramType = "path")
    })
    public Message login(@Valid Admin admin, BindingResult result, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.fail().add("errors", map);
        }
        int flag = adminService.login(admin.getAdminName(), admin.getAdminPassword());
        if (flag == 1) {
            return Message.fail().add("errors", "用户名或密码错误");
        }
        Admin loginAdmin=adminService.getLoginAdmin();
        request.getSession().setAttribute("admin",loginAdmin);
        return Message.success().add("admin",loginAdmin);
    }
}
