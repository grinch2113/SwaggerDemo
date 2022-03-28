package com.example.swaggerdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户id请求参数")
public class UserIdDto
{
	@NotNull
	@ApiModelProperty(value = "用户id",example = "2019151007")
	String id;
}
