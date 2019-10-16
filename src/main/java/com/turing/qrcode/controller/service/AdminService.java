package com.turing.qrcode.controller.service;

import com.turing.qrcode.bean.Admin;

/**
 * @author Meng
 * @date 2019/10/16
 */
public interface AdminService {
    int login(String username,String password);

    Admin getLoginAdmin();

    void setLoginAdmin(Admin loginAdmin);

}