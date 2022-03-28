package com.example.swaggerdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("添加用户请求参数")
public class UserDto
{
	@ApiModelProperty(value = "用户名",example = "2019151007")
	@NotNull
	String username;

	@ApiModelProperty(value = "密码",example = "123456")
	@NotNull
	String password;

	@ApiModelProperty(value = "昵称",example = "杜文峰")
	String nickname;

	@ApiModelProperty(value = "角色名称",example = "student")
	@NotNull
	String role;

	@ApiModelProperty(value = "用户邮箱",example = "grinch2113@gmail.com")
	@NotNull
	String email;

	public void setUsername(String username)
	{
		this.password = username;
		this.username = username;
		this.nickname = username;
	}
}
