package com.example.swaggerdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("删除用户请求参数")
public class UserDeleteDto
{
	@ApiModelProperty(value = "要删除的用户名的前缀",example = "2019")
	String prefixOfIdToDelete;

	@ApiModelProperty(value = "不删除特定用户")
	List<UserIdDto> except;
}
