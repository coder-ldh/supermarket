package com.aplus.market.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ldh
 * @Date: 2018/12/18 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPO implements Serializable {

    String name;

    String password;
}
