package com.aplus.market.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ldh
 * @since 2018-12-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("channel")
public class Channel implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**
	 * @备注:主键id
	 * @字段:id BIGINT(19)
	 */
	@TableId(type = IdType.AUTO)
	private Long id;


	/**
	 * @备注:登录账号
	 * @字段:login_name VARCHAR(255)
	 */
	private String loginName;


	/**
	 * @备注:渠道商名称
	 * @字段:name VARCHAR(255)
	 */
	private String name;


	/**
	 * @备注:推广链接
	 * @字段:link_url VARCHAR(255)
	 */
	private String linkUrl;


	/**
	 * @备注:每单分成利润比例
	 * @字段:proportion VARCHAR(255)
	 */
	private String proportion;


	/**
	 * @备注:登录密码
	 * @字段:password VARCHAR(255)
	 */
	private String password;


	/**
	 * @备注:登录token
	 * @字段:token VARCHAR(255)
	 */
	private String token;


	/**
	 * @备注:总注册会员数
	 * @字段:member_count INT(10)
	 */
	private Integer memberCount;


	/**
	 * @备注:总申请金额
	 * @字段:apply_money VARCHAR(255)
	 */
	private String applyMoney;


	/**
	 * @备注:总放款金额
	 * @字段:out_money VARCHAR(255)
	 */
	private String outMoney;


	/**
	 * @备注:总分成利润
	 * @字段:profit VARCHAR(255)
	 */
	private String profit;


	/**
	 * @备注:状态: 1,正常 2,删除
	 * @字段:status INT(10)
	 */
	private Integer status;

	/**
	 * @备注:
	 * @字段:level INT(10)
	 */
	private Integer level;
	/**
	 * 访问量
	 */
	private Integer visitorCount;

	/**
	 * @备注:
	 * @字段:partner_id VARCHAR(32)
	 */
	private Long partnerId;
}
