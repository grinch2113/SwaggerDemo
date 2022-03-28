package com.example.swaggerdemo.controller;


import com.example.swaggerdemo.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/web/template/image")
@Api(tags = "图片上传和获取操作")
public class ImageController
{
	@ApiOperation("上传图片")
	@GetMapping("/upload")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file",value = "上传的文件",dataType = "MultipartFile"),
			@ApiImplicitParam(name = "type",value = "文件类型（logo或者是背景）",dataType = "String",example = "logo")
	})
	public R upload(@RequestParam("file") MultipartFile file,@RequestParam("type") String type) {
		if (file.isEmpty()) {
			return new R().error(100,"上传失败，请选择文件");
		}
		String fileName = file.getOriginalFilename();
		String filePath = "D://logo/";
		File dest = new File(filePath + fileName);
		if (dest.exists())
		{
			dest.delete();
		}
		try {
			file.transferTo(dest);
			return new R().ok("上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			return new R().error(100,"上传失败，请选择文件");
		}
	}

	@ApiOperation("请求图片")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type",value = "文件类型（logo或背景）",dataType = "String",example = "background")
	})
	@GetMapping(value = "/getLogo",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getLogo(@RequestParam("type") String type) throws IOException
	{
		File file = new File("D://miku.jpg");
		FileInputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes, 0, inputStream.available());
		inputStream.close();
		return bytes;

	}
}