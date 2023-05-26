package com.suhuamo.web.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/12
 */
@ConfigurationProperties("suhuamo.web.mybatis")
@Data
public class MyBatisProperties {
    /**
     * 数据库url
     */
    private String url = "jdbc:mysql://localhost:3306/mysql?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&nullCatalogMeansCurrent=true";
    /**
     * 数据库账号
     */
    private String username = "root";
    /**
     * 数据库密码
     */
    private String password = "123456";
    //全局配置参数
    /**
     * 作者名称
     */
    private String author = "suhuamo";
    /**
     * 指定输出目录，默认为当前项目的java文件夹
     */
    private String outputDir = System.getProperty("user.dir") + "\\src\\main\\java";
    //包配置参数
    /**
     * 父包名
     */
    private String parent = "com.suhuamo.example";
    /**
     * Entity 实体类包名
     */
    private String entity = "pojo";
    /**
     * Mapper 包名
     */
    private String mapper = "mapper";
    /**
     * Mapper XML 包名
     */
    private String mapperXml = "mapper.xml";
    /**
     * Service 包名
     */
    private String service = "service";
    /**
     * Service Impl 包名
     */
    private String serviceImpl = "service.impl";
    /**
     * Controller 包名
     */
    private String controller = "controller";
    // 数据库表参数
    /**
     * 要生成的数据库表，可传数组,如 tbl_user, tbl_role
     */
    private List<String> tables = Arrays.asList(new String[]{"tbl_user"});
    /**
     * 忽略表前缀，可传数组，如 tbl_, sys_
     */
    private List<String> tablePrefix = Arrays.asList(new String[]{"tbl_"});
}
