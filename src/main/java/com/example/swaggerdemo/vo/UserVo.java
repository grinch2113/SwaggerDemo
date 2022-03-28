package com.example.swaggerdemo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("查询出来的用户信息")
@AllArgsConstructor
public class UserVo
{
	@ApiModelProperty(value = "用户名",example = "2019151007")
	@NotNull
	String username;


	@ApiModelProperty(value = "昵称",example = "杜文峰")
	String nickname;

	@ApiModelProperty(value = "角色名称",example = "student")
	@NotNull
	String role;

	@ApiModelProperty(value = "用户邮箱",example = "grinch2113@gmail.com")
	@NotNull
	String email;
}
