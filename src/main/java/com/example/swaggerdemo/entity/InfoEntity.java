package com.example.swaggerdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("机构信息")
public class InfoEntity
{
	@ApiModelProperty(value = "机构名称",example = "软件工程")
	String name;

	@ApiModelProperty(value = "机构地址",example = "广东省深圳市南山区南海大道3688号至理楼L1-510")
	String address;

	@ApiModelProperty(value = "机构负责人",example = "杜文峰")
	String chairman;

	@ApiModelProperty(value = "机构占地面积",example = "30")
	String coveredArea;

	@ApiModelProperty(value = "教育信息",example = "希望看到你的改变")
	String educationInfo;

	@ApiModelProperty(value = "联系电话1",example = "119")
	String contactPhoneNum1;
	@ApiModelProperty(value = "联系电话2",example = "120")
	String contactPhoneNum2;

	@ApiModelProperty(value = "官方网址",example = "www.seweb.com")
	String officialWebsite;

}
