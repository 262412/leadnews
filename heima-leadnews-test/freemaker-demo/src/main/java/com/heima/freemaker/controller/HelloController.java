package com.heima.freemaker.controller;

import com.heima.freemaker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class HelloController {
    /**
     * 处理基本的GET请求
     * 该方法用于向模型中添加数据，并返回到名为"01-basic"的FreeMarker模板
     *
     * @param model 用于存储数据的模型对象，Spring MVC会自动注入
     * @return 返回"01-basic"，表示请求将被定向到名为"01-basic"的FreeMarker模板
     */
    @GetMapping("/basic")
    public String hello(Model model) {
        // 向模型中添加一个名为"name"的属性，值为"freemaker"
        model.addAttribute("name", "freemaker");

        // 创建一个Student对象，并设置其属性
        Student student = new Student();
        student.setName("heima");
        student.setAge(18);

        // 将Student对象添加到模型中，键为"stu"
        model.addAttribute("stu", student);

        // 返回"01-basic"，表示请求将被定向到名为"01-basic"的FreeMarker模板
        return "01-basic";
    }
    // 处理学生列表请求
    @GetMapping("/list")
    public String list(Model model){
        // 创建学生对象并设置属性
        //------------------------------------

        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        // 小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);

        // 将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        // 向model中存放List集合数据
        model.addAttribute("stus",stus);

        //------------------------------------

        // 创建Map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        // 向model中存放Map数据
        model.addAttribute("stuMap", stuMap);
        return "02-list";
    }

    // 处理操作测试请求
    @GetMapping("operation")
    public String testOperation(Model model) {
        // 构建 Date 数据
        Date now = new Date();
        model.addAttribute("date1", now);
        model.addAttribute("date2", now);

        return "03-operation";
    }

    // 处理内部函数测试请求
    @GetMapping("innerFunc")
    public String testInnerFunc(Model model) {
        // 小强对象模型数据
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        // 小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        // 将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        model.addAttribute("stus", stus);
        // 添加日期
        Date date = new Date();
        model.addAttribute("today", date);
        // 添加数值
        model.addAttribute("point", 102920122);
        return "04-innerFunc";
    }

}
