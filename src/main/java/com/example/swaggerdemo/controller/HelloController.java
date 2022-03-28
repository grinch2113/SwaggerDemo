package com.example.swaggerdemo.controller;


import com.example.swaggerdemo.entity.User;
import com.example.swaggerdemo.result.R;
import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@Api(tags = "用户登录") //controller注释
public class HelloController
{

	 @ApiOperation(value = "登录接口") // controller中的接口注释
	@PostMapping(value = "/login")
	public R<User> login(@RequestBody User user){
		return new R<User>().ok("登录成功").setResponseData(user);
	}

	@ApiOperation("查找所有用户")
	@GetMapping("listAllUsers")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage",value = "当前页码",dataType = "int",example = "1"),
			@ApiImplicitParam(name = "numOfRecordsPerPage",value = "每页数据条数",dataType = "int",example = "5")
	})
	public R<List<User>> listAllUsers(@RequestParam("currentPage") @NotNull int currentPage, @RequestParam("numOfRecordsPerPage") @NotNull  int numOfRecordsPerPage)
	{
		return new R<List<User>>();
	}

	@ApiOperation("查找一部分用户")
	@GetMapping("listPartOfUsers")
	public R<List<User>> listPartOfUsers(@RequestParam("currentPage") @NotNull @ApiParam(value = "当前页码",example = "1") int currentPage,
	                                     @RequestParam("numOfRecordsPerPage") @NotNull  @ApiParam(value = "每页数据条数",example = "5") int numOfRecordsPerPage)
	{
		return new R<List<User>>();
	}
}
