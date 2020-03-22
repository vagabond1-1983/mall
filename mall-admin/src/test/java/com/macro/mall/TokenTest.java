package com.macro.mall;

import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.service.UmsAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author vaga
 * @version 2020/3/6 10:26 下午
 * @description 获取token值
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTest {
    @Resource
    private JwtTokenUtil tokenUtil;
    @Resource
    private UmsAdminService adminService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void genTokenTest() {
        UmsAdmin umsAdmin = adminService.getAdminByUsername("admin");
        UserDetails admin = new AdminUserDetails(umsAdmin,
                    adminService.getResourceList(umsAdmin.getId()));

        String token = tokenUtil.generateToken(admin);
        System.out.println("填写Authorization的值：Bearer " + token);

        System.out.println("此token的用户为：" + tokenUtil.getUserNameFromToken(token));
    }

    @Test
    public void encodePasswordTest() {
        System.out.println(passwordEncoder.encode("admin"));
    }

}
