# 一个基本web的spring-boot-start框架 web-spring-boot-starter


## 前言

1. 对于一个项目的开发，基础代码的CRUD和实体类的生成每次都需要重复创建，这样是十分麻烦的。因此本项目上线，提供了基础代码的自动生成，包括了实体类的生成，接口的增删改查等。
2. 目前版本只支持springboot3，不兼容springboot3以下的版本.
3. 从此版本开始，后续的所有版本将会上传至maven中央仓库（暂无）

## 系统需求

![jdk版本](https://img.shields.io/badge/java-17%2B-red.svg?style=for-the-badge&logo=appveyor) ![maven版本](https://img.shields.io/badge/maven-3.2.5%2B-red.svg?style=for-the-badge&logo=appveyor) ![spring boot](https://img.shields.io/badge/spring%20boot-3.0.0%2B-red.svg?style=for-the-badge&logo=appveyor)

## 当前版本

v1.0.0

## 系统介绍

- 目前整个系统分为两大模块
1. 基础模块：主要用于实现系统的增删改查自动生成功能，是默认配置的
2. 其他模块：包含了Jwt鉴权、异常处理、错误页面跳转等，可以通过yml进行取消。


### 最快上手

- 将此工程通过``mvn clean install``打包到本地仓库中。(目前1.0.0版本还没有加入maven中央仓库，无法进行直接依赖)

- 在你的工程中的``pom.xml``中做如下依赖

```xml
	<dependency>
		<groupId>com.suhuamo</groupId>
		<artifactId>web-spring-boot-starter</artifactId>
		<version>1.0.0</version>
	</dependency>
```

- 在``application.properties``或者``application.yml``中做如下的配置：（至于以上的配置说明后面的章节会讲到）

```yaml
spring:
  #数据源配置
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/springboot?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&
# web-starter的配置
suhuamo:
  web:
    mybatis:
      # 生成的包路径
      parent: com.suhuamo.example
      # 需要自动生成代码的数据库表
      tables:
        - tbl_user
        - tbl_emp
        - tbl_role
      # 生成代码时的java类去掉前缀，可为空
      tablePrefix:
        - tbl
```
- 以上配置好以后就可以写demo测试啦，首先创建进行代码的生成：

```java
@SpringBootTest
public class exampleTest {

    @Autowired
    MyBatisService myBatisPlusService;

    @Test
    public void GeneraCode() throws IOException {
        myBatisPlusService.generatorCode();
    }

}
```
点击运行后可发现代码已自动生成：如下
![img.png](img.png)
- 代码生成完毕后，就可以运行项目测试接口了,一些是一些基础的接口 xxx/方法 ，xxx为对应的页面，如 user、emp 等
```java
1.
    /**
     * 查询所有数据
     *
     * @param
     * @return BaseResponse
     */
    @GetMapping("/all")
    public R all(){
    }
2.

    /**
     * 查询所有数据，可选择使用各种匹配查询,通过传递的值来选择匹配方式
     *
     * @param condition 匹配查询的内容，Map<String, List<Pair<String, Object>>> ，第一个String为匹配模式，like、eq等，
     *                  List<Pair<String, Object>>为匹配的内容，first为项，second为值
     * @return BaseResponse
     */
    @GetMapping("/allByCondition")
    public R allByCondition(@RequestParam Map<String, List<Pair<String, Object>>> condition) {
    }
3.
    /**
     * 通过分页查询获取数据，可选择使用模糊查询
     *
     * @param pageNum 页码
     * @param pageSize 页面数据大小
     * @param condition 模糊查询的条件-
     *                     Map<String, List<Pair<String, Object>>>第一个String为匹配模式，like、eq等，
     *                     List<Pair<String, Object>>为匹配的内容，first为项，second为值
     * @return BaseResponse
     */
    @GetMapping("/list")
    public R list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("condition") Map<String, List<Pair<String, Object>>> condition){
    }
4.
    /**
     *  根据id查询数据
     * @param id
     * @return BaseResponse
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
    }
5.
    /**
     * 添加数据
     *
     * @param entityDTO
     * @return BaseResponse
     */
    @PostMapping("/add")
    public R add(@RequestBody D entityDTO){
    }
6.
    /**
     * 修改数据，根据id修改其他属性
     *
     * @param entityDTO
     * @return T
     */
    @PutMapping("/update")
    public R update(@RequestBody D entityDTO){
    }
7.
    /**
     *  删除数据,可进行批量删除,使用,隔开
     * @param ids
     * @return RespMessage
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") String ids){
    }
```