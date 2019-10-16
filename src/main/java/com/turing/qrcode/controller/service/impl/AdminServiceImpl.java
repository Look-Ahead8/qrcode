package com.turing.qrcode.controller.service.impl;

import com.turing.qrcode.bean.Admin;
import com.turing.qrcode.controller.service.AdminService;
import com.turing.qrcode.dao.AdminMapper;
import com.turing.qrcode.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Meng
 * @date 2019/10/16
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    private Admin loginAdmin=null;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return 0：登录成功
     * 1：用户名或密码错误
     */
    @Override
    public int login(String username, String password) {
        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null || !admin.getAdminPassword().equals(MD5Util.md5(password))) {
            return 1;
        }
        this.loginAdmin=admin;
        return 0;
    }

    public Admin getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(Admin loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

}
