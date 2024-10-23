package com.heima.freemarker.test;

import com.heima.freemaker.FreemarkerDemoApplication;
import com.heima.freemaker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SpringBootTest(classes = FreemarkerDemoApplication.class)
@RunWith(SpringRunner.class)
public class FreemarkerTest {
    // 自动注入Configuration对象，用于后续获取模板
    @Autowired
    private Configuration configuration;

    // 测试方法，用于演示如何使用FreeMarker模板生成HTML文件
    @Test
    public void test() throws IOException, TemplateException {
        // 获取名为"02-list.ftl"的模板
        Template template = configuration.getTemplate("02-list.ftl");
        // 将数据模型处理并写入到指定的文件中
        template.process(getData(), new FileWriter("d:/list.html"));
    }

    /**
     * 构建数据模型的方法
     * 该方法创建并返回一个包含学生信息的Map对象，用于FreeMarker模板的数据处理
     * 主要包括两个部分的数据：学生对象列表和学生对象的Map
     * @return 包含学生信息的数据模型
     */
    private Map getData() {
        // 创建一个HashMap用于存储数据模型
        Map<String, Object> map = new HashMap<>();

        // 创建并配置第一个学生对象小强
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        // 创建并配置第二个学生对象小红
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);

        // 将两个学生对象添加到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        // 将包含学生对象的List集合放入数据模型中
        map.put("stus", stus);

        // 创建一个HashMap用于存储学生对象
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);

        // 将包含学生对象的HashMap放入数据模型中
        map.put("stuMap", stuMap);

        // 返回构建好的数据模型
        return map;
    }
}
