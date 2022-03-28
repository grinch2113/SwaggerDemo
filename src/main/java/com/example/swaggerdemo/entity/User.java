package com.example.swaggerdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类") // 实体类注释，在swagger页面下面model会显示实体类
public class User implements Serializable
{
	public static final long serialVersionUID = 42L;


	@Length(min=6,max = 8,message = "用户名长度为6-8位")
	@ApiModelProperty(value = "用户名", example = "miku")
	 String username;

	@NotNull(message = "密码不能为空")
	@ApiModelProperty(value = "密码", example = "123456")
	 String password;
}
