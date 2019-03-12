package com.aplus;

import com.alibaba.fastjson.JSON;
import com.aplus.market.constant.JwtConstant;
import com.aplus.market.model.Admin;
import com.aplus.market.service.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelServiceApplicationTests {

    @Autowired
    private JwtService jwtService;

    @Test
    public void contextLoads() {
        Admin admin = new Admin();
        admin.setId(123456L);
        admin.setUserName("ldh");
        admin.setPhone("13477712166");
        String subject = JSON.toJSONString(admin);

        try {
            String jwt = jwtService.createJWT(admin.getId().toString(), "Anson", subject, JwtConstant.JWT_TTL);
            System.out.println("JWT：" + jwt);
            System.out.println("\n解密\n");
            Claims c = jwtService.parseJWT(jwt);
            System.out.println(c.getId());
            System.out.println(c.getIssuedAt());
            System.out.println(c.getSubject());
            System.out.println(c.getIssuer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
