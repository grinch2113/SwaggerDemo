# 接口文档


<a name="overview"></a>
## 概览
该文档描述了Hello Controller


### 版本信息
*版本* : 1.1


### URI scheme
*域名* : localhost:8080  
*基础路径* : /


### 标签

* 用户登录 : Hello Controller




<a name="paths"></a>
## 路径

<a name="loginusingpost"></a>
### 登录接口
```
POST /login
```


#### Body参数
user

*名称* : user  
*标志* : 必填  
*类型* : [用户实体类](#c2d667b8a3f2d3260c2fffc9b5454834)


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[统一返回结果«用户实体类»](#b087fead2171e2830811a1376e2c3641)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`


#### 生成

* `*/*`


#### 标签

* 用户登录


#### HTTP请求示例

##### 请求 path
```
/login
```


##### 请求 body
```
json :
{
  "password" : "123456",
  "username" : "miku"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "code" : 200,
  "message" : "操作成功",
  "success" : true,
  "data" : {
    "password" : "123456",
    "username" : "miku"
  }
}
```




<a name="definitions"></a>
## 定义

<a name="c2d667b8a3f2d3260c2fffc9b5454834"></a>
### 用户实体类

|名称|说明|类型|
|---|---|---|
|**password**  <br>*可选*|密码  <br>**样例** : `"123456"`|string|
|**username**  <br>*可选*|用户名  <br>**样例** : `"miku"`|string|


<a name="b087fead2171e2830811a1376e2c3641"></a>
### 统一返回结果«用户实体类»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|响应码  <br>**样例** : `200`|integer (int32)|
|**data**  <br>*可选*|相应参数  <br>**样例** : `"[c2d667b8a3f2d3260c2fffc9b5454834](#c2d667b8a3f2d3260c2fffc9b5454834)"`|[用户实体类](#c2d667b8a3f2d3260c2fffc9b5454834)|
|**message**  <br>*可选*|消息  <br>**样例** : `"操作成功"`|string|
|**success**  <br>*可选*|操作是否成功  <br>**样例** : `true`|boolean|





