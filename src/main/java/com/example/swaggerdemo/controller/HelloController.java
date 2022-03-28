package com.example.swaggerdemo.controller;


import com.example.swaggerdemo.entity.User;
import com.example.swaggerdemo.result.R;
import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "用户登录") //controller注释
public class HelloController
{

	 @ApiOperation(value = "登录接口") // controller中的接口注释
	@PostMapping(value = "/login")
	public R<User> login(@RequestBody User user){
		return new R<User>().ok("登录成功").setResponseData(user);
	}
}
