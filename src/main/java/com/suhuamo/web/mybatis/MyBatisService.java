package com.suhuamo.web.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.suhuamo.web.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/03
 * mybatis-plus代码自动生成器，生成模板在templates文件夹下可修改,注意，该类得使用 mybatis-plus 3.5.3 的版本
 */
@Slf4j
public class MyBatisService {

    MyBatisProperties myBatisProperties;

    /**
     * java文件的文件后缀
     */
    private final static String JAVA_FILE_SUFFIX = ".java";

    public MyBatisService(MyBatisProperties myBatisProperties) {
        this.myBatisProperties = myBatisProperties;
    }

    public void generatorCode() throws IOException {
        // 生成mybatis-plus定义的文件
        createFastAutoGenerator(myBatisProperties.getUrl(), myBatisProperties.getUsername(), myBatisProperties.getPassword(), myBatisProperties.getAuthor(), myBatisProperties.getOutputDir(), myBatisProperties.getParent(), myBatisProperties.getEntity(), myBatisProperties.getMapper(), myBatisProperties.getMapperXml(), myBatisProperties.getService(), myBatisProperties.getServiceImpl(), myBatisProperties.getController(), myBatisProperties.getTables(), myBatisProperties.getTablePrefix());
        // 创建其他自定义文件
        createOtherFile(myBatisProperties.getOutputDir(), myBatisProperties.getParent(), myBatisProperties.getEntity());
    }

    /**
     *  代码生成器
     * @param url
     * @param username
     * @param password
     * @param author
     * @param outputDir
     * @param parent
     * @param entity
     * @param mapper
     * @param mapperXml
     * @param service
     * @param serviceImpl
     * @param controller
     * @param tables
     * @param tablePrefix
     * @return void
     */
    private void createFastAutoGenerator(String url, String username, String password, String author, String outputDir, String parent, String entity, String mapper, String mapperXml, String service, String serviceImpl, String controller, List<String> tables, List<String> tablePrefix) {
        //  开始生成
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(author)//设置作者名
                            .outputDir(outputDir)//设置文件输出路径
                            .commentDate("yyyy-MM-dd")//注释日期
                            .disableOpenDir();//运行完成不打开生成目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent)
                            .entity(entity)
                            .mapper(mapper)
                            .xml(mapperXml)
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller);
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)//需要生成的数据表，可传list
                            .addTablePrefix(tablePrefix)//忽略表前缀，可传list
                            //开启生成实体类
                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //开启生成mapper
                            .mapperBuilder()
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .superClass(BaseMapper.class)//设置父类
                            .formatMapperFileName("%sMapper")//格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper")//格式化 xml 实现类文件名称
                            //开启生成service及impl
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 实现类文件名称
                            //开启生成controller
                            .controllerBuilder()
                            .enableHyphenStyle()// 映射路径使用连字符格式，而不是驼峰
                            .formatFileName("%sController")//格式化文件名称
                            .enableRestStyle();
                })
                .templateConfig(builder -> {
                })
                .execute();
    }

    /**
     * 进行其他文件的自动生成
     *
     * @param outputDir
     * @param parent
     * @param entity
     * @return void
     */
    private void createOtherFile(String outputDir, String parent, String entity) throws IOException {
        // 将包名转换为目录格式
        String replace = parent.replace(".", "/");
        // 获取 entity 文件夹
        File fileDirectory = new File(outputDir + "/" + replace + '/' + entity);
        // 创建vo
        File voFileDirectory = new File(fileDirectory + "/vo");
        voFileDirectory.mkdir();
        // 获取每一个文件
        for (File file : fileDirectory.listFiles()) {
            // 忽略 BaseResponse.java文件
            if(file.getName().equals("BaseResponse.java")) {
                continue;
            }
            // 对 java文件操作，而不是文件夹
            if (!file.isDirectory()) {
                // 获取JAVA名
                String name = file.getName();
                name = name.substring(0, name.lastIndexOf(JAVA_FILE_SUFFIX));
                // 获取文件路径
                String pojoFile = file.getAbsolutePath();
                // 读取内容
                String content = readFile(pojoFile);
                // 创建vo
                creatVO(name, pojoFile, content, entity, "vo", "VO");
                // 首字母小写
                String firstLetter = name.substring(0, 1).toLowerCase();
                String restLetters = name.substring(1);
                String result = firstLetter + restLetters;
                // 创建dto文件夹
                File dtoDileDirectory = new File(fileDirectory + "/dto/" + result);
                dtoDileDirectory.mkdirs();
                // 创建dto文件
                creatDTO(name, pojoFile, content, entity, "dto/" + result, "AddDTO");
                creatDTO(name, pojoFile, content, entity, "dto/" + result, "UpdateDTO");
                creatQueryDTO(name, pojoFile, content, entity, "dto/" + result, "QueryDTO");
            }
        }
    }

    /**
     *  创建 vo 文件
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private void creatVO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建vo类文件
        String voFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // vo内容
        List<String> voContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if(directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    voContentList.add(vo_line);
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " implements Serializable");
                    voContentList.add(vo_line);
                }
                // 注释需要修改
                else if(line.contains("数据表实体类")) {
                    String vo_line = "";
                    // 如果是dto
                    if(suffix.equals("DTO")) {
                        vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    } else {
                        vo_line = line.replace("数据表实体类", "后端->前端数据显示类");
                    }
                    voContentList.add(vo_line);
                }
                // 其他都一样，无需处理，直接添加
                else {
                    voContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : voContentList) {
                voContent.append(s);
            }
            try {
                writeFile(voFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.shutdown();
    }

    /**
     *  创建 dto 文件
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private void creatDTO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建vo类文件
        String voFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // vo内容
        List<String> voContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if(directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    voContentList.add(vo_line);
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " implements Serializable");
                    voContentList.add(vo_line);
                }
                // 注释需要修改
                else if(line.contains("数据表实体类")) {
                    String vo_line = "";
                    // 如果是dto
                    if(suffix.equals("DTO")) {
                        vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    } else {
                        vo_line = line.replace("数据表实体类", "后端->前端数据显示类");
                    }
                    voContentList.add(vo_line);
                }
                // 其他都一样，无需处理，直接添加
                else {
                    voContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : voContentList) {
                voContent.append(s);
            }
            try {
                writeFile(voFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.shutdown();
    }

    /**
     *  创建 Querydto 文件
     * @param name
     * @param pojoFile
     * @param content
     * @param entity
     * @param directory
     * @param suffix
     * @return void
     */
    private void creatQueryDTO(String name, String pojoFile, String content, String entity, String directory, String suffix) {
        // 创建vo类文件
        String voFile = pojoFile.replace(name + JAVA_FILE_SUFFIX, directory + "/" + name + suffix + JAVA_FILE_SUFFIX);
        // 对内容进行按行分类
        String[] split = content.split("\n");
        // vo内容
        List<String> voContentList = new ArrayList<>();
        // 进行内容处理，即删除不需要的和修改
        for (String line : split) {
            // 需要忽略的行
            if (line.contains("@Id") || line.contains("@TableId") || line.contains("@Column") || line.contains("@TableField") || line.contains("@Entity") || line.contains("@Table")) {
                continue;
            } else {
                // 包路径需要修改
                if (line.startsWith("package")) {
                    if(directory.contains("/")) {
                        directory = directory.replace("/", ".");
                    }
                    String vo_line = line.replace(entity, entity + "." + directory);
                    voContentList.add(vo_line);
                    // 添加 PageProperties 的包
                    voContentList.add("import com.suhuamo.web.common.PageProperties;");
                }
                // 类名需要修改
                else if (line.startsWith("public class " + name + " implements Serializable")) {
                    String vo_line = line.replace("public class " + name + " implements Serializable", "public class " + name + suffix + " extends PageProperties implements Serializable");
                    voContentList.add(vo_line);
                }
                // 注释需要修改
                else if(line.contains("数据表实体类")) {
                    String vo_line = line.replace("数据表实体类", "前端->后端数据传输类");
                    voContentList.add(vo_line);
                }
                // 其他都一样，无需处理，直接添加
                else {
                    voContentList.add(line);
                }
            }
        }
        // 进行线程处理
        ExecutorService thread = ThreadPoolUtil.getThread();
        thread.execute(() -> {
            // 创建vo文件
            StringBuilder voContent = new StringBuilder();
            for (String s : voContentList) {
                voContent.append(s);
            }
            try {
                writeFile(voFile, String.valueOf(voContent));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.shutdown();
    }


    /**
     * 读取文件的所有内容
     *
     * @param path
     * @return String
     */
    private String readFile(String path) throws IOException {
        // 获取文件对象
        File file = new File(path);
        // 读取文件内容 (输入流)
        FileInputStream out = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(out);
        // 进入读取数据
        StringBuilder content = new StringBuilder(""); // 返回文件的所有内容
        int ch = 0; // 用来接收输入流的每一次数据
        while ((ch = isr.read()) != -1) {
            content.append((char) ch);
        }
        // 关闭流
        isr.close();
        out.close();
        return content.toString();
    }

    /**
     * 将 content 内容以覆盖的形式写入 path 文件中
     *
     * @param path
     * @param content
     * @return void
     */
    private void writeFile(String path, String content) throws IOException {
        // 创建文件
        File file = new File(path);
        if(file.createNewFile()) {
            log.info("创建{}文件成功",path);
        }

        // 进行写入数据
        byte[] bt = content.getBytes();
        // 以覆盖的形式，加上true参数代表追加
        FileOutputStream in = new FileOutputStream(file);
        in.write(bt, 0, bt.length);
        // 关闭流
        in.close();
    }
}


