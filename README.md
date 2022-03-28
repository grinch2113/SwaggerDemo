# SpringBoot集成Swagger2

* jdk版本：1.8

SpringBoot集成了Swagger后，可以非常方便前后端进行接口参数的沟通和接口的测试。Swagger会显示每个接口的请求参数，请求参数示例，响应参数，响应参数示例。点击“Try it out”按钮后，就可以测试这个接口，方便前端确认接口的请求参数和响应参数是否正确。



## 步骤

0. [创建项目](#NewProject)

1. [导入Maven依赖](#MavenDependencies)
2. [编写SpringBoot的一个demo](#SpringBootDemo)
3. [配置Swagger](#SwaggerConfig)
4. [给接口和实体类添加注释](#AddComment)



<a name="NewProject"></a>

## 创建项目

File - New - Project 选Maven项目

![image-20220328200458201](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328200458201.png)



<a name="MavenDependencies"></a>

## 导入Maven依赖

* SpringBoot 父工程依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.7.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```



* SpringBoot web依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```



* swagger的内核和动态文档展示的依赖

```xaml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```



<a name="SpringBootDemo"></a>

## 编写SpringBoot的一个demo

post请求中，前端传过来的是json。我们用User对象来接收参数，@RequestBody注解会帮我们用输入流的方式将json数据转换成User对象。

后端返回数据时，用一个响应结果实体类来装载响应数据。该类的属性有响应码code，响应信息message，是否成功success，返回的数据data。该类是泛型类，返回的数据用Java类封装起来之后，传给该响应结果类。通过@RestController或@ResponseBody注解将响应结果对象转换成json后传给前端。

### 启动类

```java
@SpringBootApplication
public class SwaggerdemoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SwaggerdemoApplication.class, args);
	}

}
```



### 实体类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable
{
	public static final long serialVersionUID = 42L;


	@Length(min=6,max = 8,message = "用户名长度为6-8位")
	 String username;

	@NotNull(message = "密码不能为空")
	 String password;
}
```



### 响应结果实体类

```java
@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer code;

	String message;

	Boolean success;

	T data;

	public R() {
		this.code = 0;
		this.message = "success";
		this.success = true;
	}

	public R ok(String message) {
		setMessage(message);
		return this;
	}

	public R setResponseData(T obj)
	{
		data = obj;
		return this;
	}
}
```



### 控制层

```java
@RestController
public class HelloController
{

	@PostMapping(value = "/login")
	public R<User> login(@RequestBody User user){
		return new R<User>().ok("登录成功").setResponseData(user);
	}
}
```



 ### 访问Swagger

<a name="swagger-ui"></a>

启动项目后，访问http://localhost:8080/swagger-ui.html

![image-20220328171808805](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328171808805.png)



<a name="SwaggerConfig"></a>

## 配置Swagger

通过配置Swagger，可以修改 [swagger-ui](#swagger-ui) 文档中的标题，描述，分组等

### 新建SwaggerConfig类

配置说明如下代码。@Configration和@Bean会将该函数的返回值装配到Spring容器中，Swagger内核从Spring容器中获取该配置。

```java

// swagger配置类
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo(
						"接口文档",       					 // swagger页面标题
						"该文档描述了Hello Controller",   	// swagger页面描述
						"1.1",          					// 标题右边的版本号
						"",      							// 留空
						new Contact("", "", ""),   			// 作者联系方式
						"",									// license
						"",									// license的url
						new ArrayList()))
				.groupName("my")  // 分组名称
				// 指定扫描接口的包，select和build成组出现
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
				.build();
	}
}
```

__重启项目__，再次打开http://localhost:8080/swagger-ui.html，可以发现标题等内容被修改

![image-20220328174036984](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328174036984.png)



在右上角的下拉框显示的是所有组，一个docket对应一个组。可以在配置类中，多配置一个docket，这样就有两个组了。

```java
// swagger配置类
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    // 新的组，相比旧的组修改了页面的标题和组名
    @Bean
	public Docket docket1()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo(
						"第二个接口文档",       				// swagger页面标题
						"该文档描述了Hello Controller",   	// swagger页面描述
						"1.1",          					// 标题右边的版本号
						"",      							// 留空
						new Contact("", "", ""),   			// 作者联系方式
						"",									// license
						"",									// license的url
						new ArrayList()))
				.groupName("my1")  // 分组名称
				// 指定扫描接口的包，select和build成组出现
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
				.build();
	}
    
    
    // 旧的组
	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo(
						"接口文档",       					 // swagger页面标题
						"该文档描述了Hello Controller",   	// swagger页面描述
						"1.1",          					// 标题右边的版本号
						"",      							// 留空
						new Contact("", "", ""),   			// 作者联系方式
						"",									// license
						"",									// license的url
						new ArrayList()))
				.groupName("my")  // 分组名称
				// 指定扫描接口的包，select和build成组出现
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
				.build();
	}
}
```

__重启项目__，再次打开http://localhost:8080/swagger-ui.html 可以发现下拉框中有第二个组

![image-20220328174923084](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328174923084.png)

点进去后，标题是第二个组的标题

![image-20220328175030710](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328175030710.png)



* 通过配置不同组扫描的包，可以让不同的人负责写各自的接口信息

<a name="AddComment"></a>

## 给接口和实体类添加注释

在 [swagger-ui](#swagger-ui)  页面中，点开hello-controller，只有参数名和参数的类型。单这样看很难看出接口的功能。我们还需要给接口添加注释和参数示例。

### 给接口添加注释

常用的注解如下

* @Api

作用：标记在controller类上，说明该controller的功能

参数：`tags：controller的注释`



* @ApiOperation

作用：标记在使用 @RequestMapping 来映射请求的方法上，说明该接口的功能

参数：`value: 接口的注释`



使用这两个注解后，效果如下，与 [swagger-ui](#swagger-ui)  对比，可以发现controller和这个controller里面的/login接口都有相应的注释了

![image-20220328182710456](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328182710456.png)









* ApiImplicitParams

作用：用于get请求，标记在使用 @GetMapping 来映射请求的方法上，说明该接口有哪些参数，与ApiImplicitParam配合使用

* ApiImplicitParam

作用：用于ApiImplicitParams注解内，说明接口的其中一个参数



两者使用示例：

```java
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage",value = "当前页码",dataType = "int",example = "1"),
			@ApiImplicitParam(name = "numOfRecordsPerPage",value = "每页数据条数",dataType = "int",example = "5")
	})
```



效果如下：![image-20220329013500875](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220329013500875.png)

其中ApiImplicitParam的example参数将在测试接口时当成默认值

点击try it out，效果如下

![image-20220329013621754](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220329013621754.png)





* @ApiParam

标记在参数列表中的一个参数上，效果与@ApiImplicitParam一样，但是不需要指定dataType





添加注释后的controller代码如下

```java
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

```







### 给实体类添加注释

常用的注解如下

* @ApiModel

作用：标记在实体类上，给这个实体类添加注释

参数：

	1. value 实体类的注释



* @ApiModelProperty

作用：标记在实体类的属性上，给这个属性添加注释

参数：

 	1. value 实体类属性的注释
 	2. position 这个属性在swagger文档对应的model中的显示顺序
 	3. example 这个属性的示例值



给User类添加注释后的代码如下

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类") // 实体类注释，在swagger页面下面model会显示实体类
public class User implements Serializable
{
   public static final long serialVersionUID = 42L;


   @Length(min=6,max = 8,message = "用户名长度为6-8位")
   @ApiModelProperty(value = "用户名", example = "miku")
    String username;

   @NotNull(message = "密码不能为空")
   @ApiModelProperty(value = "密码", example = "123456")
    String password;
}
```



给响应结果类添加注释后的代码如下

```java
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


   public R setResponseData(T obj)
   {
      data = obj;
      return this;
   }
}
```



与 [swagger-ui](#swagger-ui)  对比，可以发现接口里的请求参数和响应参数都有注释和示例了，下面的model也有注释和示例了

![image-20220328184756332](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328184756332.png)

![image-20220328184826057](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328184826057.png)



__至此，已完成Swagger的集成和配置。接下来就是将其导出成静态文档__



# Swagger导出静态文档

Springboot集成了Swagger后，访问http://localhost:8080/swagger-ui.html生成的是动态文档。有时候我们还需要将其导出成pdf等格式的静态文档给其他人阅读。手打工作量太大，我们可以通过代码让其自动生成静态文档。下



步骤：

1. 引入Maven依赖
2. 编写测试函数



## 引入Maven依赖

```xml
<!--swagger2markup-->
<dependency>
    <groupId>io.github.swagger2markup</groupId>
    <artifactId>swagger2markup</artifactId>
    <version>1.3.1</version>
</dependency>
<dependency>
    <groupId>ch.netzwerg</groupId>
    <artifactId>paleo-core</artifactId>
    <version>0.10.2</version>
</dependency>
<dependency>
    <groupId>io.vavr</groupId>
    <artifactId>vavr</artifactId>
    <version>0.9.2</version>
</dependency>
```



### 编写测试类

```java

@SpringBootTest(classes = SwaggerdemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class testDocs
{
	// 群组名称
	 final String group = "my";


	/**
	 * 生成md格式文档
	 * @throws Exception
	 */
	@Test
	public void generateMarkdownFile() throws Exception {
		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
				// 指定文件格式为markdown
				.withMarkupLanguage(MarkupLanguage.MARKDOWN)
				// 指定语言为中文
				.withOutputLanguage(Language.ZH)
				// 生成参数示例
				.withGeneratedExamples()
				// 生成请求体
				.withFlatBody()
				.withoutInlineSchema()
				.build();
		// 获取接口数据的url，请求方式是get，若没有分组则不需要group参数
		URL apiUrl = new URL("http://localhost:8080/v2/api-docs?group="+group);

		// 指定文件名称
		String markdownFileName = "src/docs/markdown/generated/api";

		// 指定获取接口数据的url
		Swagger2MarkupConverter.from(apiUrl)
				// 指定配置信息
				.withConfig(config)

				.build()
				//指定生成目录下生成指定文件
				.toFile(Paths.get(markdownFileName));
	}
}
```

其中，获取接口数据的url在 http://localhost:8080/swagger-ui.html 中有显示，如下图的红框

![image-20220328190247925](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328190247925.png)



执行这个测试方法，可以在项目的src/docs/markdown/generated/目录下找到输出的markdown文件api.md

![image-20220328190526293](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328190526293.png)

至此，成功生成静态文档。只需要将md转换成想要的格式，如pdf或doc等。



# 可能遇到的错误

## 跑测试方法时，Failed to load ApplicationContext

主程序正在运行，端口被占用了，将主程序停掉即可



## 生成ASCIIDOC，跑测试方法时，Error creating extended parser class

错误详细信息

java.lang.RuntimeException: Error creating extended parser class: Could not determine whether class 'org.pegdown.Parser$$parboiled' has already been loaded



原因：jdk版本太高（jdk17会报这个错）

解决方法：

打开Run/Debug Configurations

![image-20220328191349516](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328191349516.png)





将jdk调成1.8

![image-20220328191547091](https://raw.githubusercontent.com/grinch2113/SwaggerDemo/master/assert/image-20220328191547091.png)
