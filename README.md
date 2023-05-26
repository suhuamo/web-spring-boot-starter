# 一个异常通知的spring-boot-start框架 prometheus-spring-boot-starter


写SQL老是拼写错误？试试去SQL化的查询方式[https://gitee.com/ITEater/ameba-spring-boot-starter](https://gitee.com/ITEater/ameba-spring-boot-starter)

## 前言

1. 对于工程的开发，必然会伴随着各种bug，工程量越大，出现bug的概率也会越高。一般对于代码量较小的工程来说，一个人可能就足够去做开发与维护；但是对于代码量较大的工程往往是需要一个小团队协作开发。当工程基本完成，开始部署测试环境或者生产环境时，这些环境并不能像开发环境一样能快速的调试与维护，线上的工程一旦出现异常时，开发团队就需要主动感知异常并协调处理，当然人不能一天24小时去盯着线上工程，所以就需要一种机制来自动化的对异常进行通知，并精确到谁负责的那块代码。这样会极大地方便后续的运维。因此，本项目上线
2. 目前版本只支持springboot3，不再兼容springboot3以下的版本,**需要看springboot2版本的可以看0.8.0以前的分支**
3. 从此版本开始，后续的所有版本将会上传至maven中央仓库

## 系统需求

![jdk版本](https://img.shields.io/badge/java-17%2B-red.svg?style=for-the-badge&logo=appveyor) ![maven版本](https://img.shields.io/badge/maven-3.2.5%2B-red.svg?style=for-the-badge&logo=appveyor) ![spring boot](https://img.shields.io/badge/spring%20boot-3.0.0%2B-red.svg?style=for-the-badge&logo=appveyor)

## 当前版本

![目前工程版本](https://img.shields.io/badge/version-0.8.0-green.svg?style=for-the-badge&logo=appveyor)

## 系统介绍

- 目前整个系统分为两大模块
1. 异常通知模块：主要用于处理在项目中曝出的未捕获异常，通过钉钉或者邮件等方式进行主动消息通知
2. 微服务通知模块：主要用于对微服务集群中服务信息异常、缺失等情况，通过钉钉或邮件等方式进行消息通知


## 异常通知


### 最快上手

- 将此工程通过``mvn clean install``打包到本地仓库中。(目前0.8.0版本已加入maven中央仓库，可以直接进行依赖即可)

- 在你的工程中的``pom.xml``中做如下依赖

```xml
	<dependency>
		<groupId>top.codef</groupId>
		<artifactId>prometheus-spring-boot-starter</artifactId>
		<version>0.8.0</version>
	</dependency>
```

- 在``application.properties``或者``application.yml``中做如下的配置：（至于以上的配置说明后面的章节会讲到）

```yaml
prometheus:
  enabled: true
  exceptionnotice:
    enabled: true
    included-trace-package: com.havefun
  notice:
    dingding:
      user1:
        access-token: 在webhook中的参数：accessToken
        sign-secret: 钉钉的签名秘钥
        enable-signature-check: true
        phone-num: 
        - 通知人的手机号
  default-name: user1
```

- 至于钉钉的配置请移步：[钉钉机器人](https://open-doc.dingtalk.com/microapp/serverapi2/krgddi "自定义机器人")，这里需要特别说明一下，钉钉在2019年10月份（大概）对于钉钉机器人进行了一次改进,这次改进主要是安全设置变为必选项，原来已经配置过的钉钉机器人且没有配置安全设置的不受影响

- 以上配置好以后就可以写demo测试啦，首先创建第一个bean：

```java
@Component
@ExceptionListener
public class SomeComponent {
	public String haveException() {
		throw new NullPointerException("这是个空指针异常");
	}
}
```

- 以上都建立好了以后，就可以写单元测试了:
```java
@SpringBootTest
class PrometheusDemoApplicationTests {

	@Autowired
	private SomeComponent someComponent;

	@Test
	void contextLoads() {
		someComponent.haveException();
	}
}