package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.dto.UserDeleteDto;
import com.example.swaggerdemo.dto.UserDto;
import com.example.swaggerdemo.dto.UserIdDto;
import com.example.swaggerdemo.result.R;
import com.example.swaggerdemo.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/uaa/sys/user")
@Api(tags = "用户数据的操作")
public class UserController
{
	@ApiOperation("添加用户")
	@PostMapping("addUser")
	public R addUser(@RequestBody List<UserDto> userList)
	{
		return new R().ok("成功添加用户");
	}

	@ApiOperation("根据用户名查找用户")
	@GetMapping("findUserById")
	public R<UserVo> findUser(@RequestBody UserIdDto userIdDto)
	{
		return new R().ok("成功查找用户").setResponseData(null);
	}

	@ApiOperation("修改用户信息")
	@PostMapping("updateUser")
	public R updateUser(@RequestBody UserDto userDto){
		return new R().ok("修改成功");
	}

	@ApiOperation("根据id删除用户")
	@PostMapping("removeUserById")
	public R deleteUser(@RequestBody UserDeleteDto userDeleteDto)
	{
		return new R().ok("删除成功");
	}

	@ApiOperation("查找所有用户")
	@GetMapping("listAllUsers")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage",value = "当前页码",dataType = "int",example = "1"),
			@ApiImplicitParam(name = "numOfRecordsPerPage",value = "每页数据条数",dataType = "int",example = "5")
	})
	public R<List<UserVo>> listAllUsers(@RequestParam("currentPage") @NotNull  int currentPage, @RequestParam("numOfRecordsPerPage") @NotNull  int numOfRecordsPerPage)
	{
		return new R<List<UserVo>>();
	}
}
