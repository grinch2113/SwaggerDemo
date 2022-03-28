
package com.example.swaggerdemo.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.http.HttpStatus;
import java.io.Serializable;


/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@ToString
@ApiModel(value = "统一返回结果")
@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应码",position = 1,example = "200") // position属性显式地在参数中的顺序
	Integer code;

	@ApiModelProperty(value = "消息",position = 2,example = "操作成功")
	String message;

	@ApiModelProperty(value = "操作是否成功",position = 3,example = "true")
	Boolean success;

	@ApiModelProperty(value = "相应参数",position = 4)
	T data;

	public R() {
		this.code = 0;
		this.message = "success";
		this.success = true;
	}

	public R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}

	public R error(String message) {
		return new R().error(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
	}

	public R error(int code, String message) {
		setMessage(message);
		setCode(code);
		setSuccess(false);
		return this;
	}
	public R error(ResultCodeEnum resultCodeEnum) {
		setCode(resultCodeEnum.getCode());
		setMessage(resultCodeEnum.getMsg());
		setSuccess(false);
		return this;
	}

	public R ok(String message) {
		setMessage(message);
		return this;
	}


	public <T> T getData(String key, TypeReference<T> tTypeReference) {
		String toJSONString = JSON.toJSONString(data);
		T t = JSON.parseObject(toJSONString, tTypeReference);
		return t;
	}

	public R setResponseData(T obj)
	{
		data = obj;
		return this;
	}
}

