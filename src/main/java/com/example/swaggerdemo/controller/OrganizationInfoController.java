package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.entity.InfoEntity;
import com.example.swaggerdemo.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/web/template/orgInfo")
@Api(tags = "获取和修改机构信息操作")
public class OrganizationInfoController
{


	@ApiOperation("修改机构信息")
	@PostMapping("update")
	@ResponseBody
	public R setInfo(@RequestBody InfoEntity info)
	{
		return new R().ok("成功修改机构信息");
	}

	@ApiOperation("获取机构信息")
	@GetMapping("get")
	public R<InfoEntity> getInfo()
	{
		return new R<InfoEntity>().ok("成功获取机构信息").setResponseData(null);
	}

}
