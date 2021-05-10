package com.xiaomazi.springboot.web;

import com.xiaomazi.springboot.model.Student;
import com.xiaomazi.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 15-springboot-Advanced
 * @description
 * @author: XiaoMaZi
 * @create: 2021-05-08 14:33
 **/
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/student")
    public String queryStudent(Model model,Integer id){

        Student student = studentService.queryByPrimaryKey(id);

        model.addAttribute("data",student);

        return "showDetail";
    }

    @RequestMapping("/student2")
    public String queryStudent2(Model model,Integer id){

        Student student = studentService.queryByPrimaryKey(id);

        model.addAttribute("data",student);


        int a = 1/0;

        return "showDetail";
    }

    @RequestMapping("/student3")
    public String queryStudent3(Model model,Integer id) throws FileNotFoundException {

        Student student = studentService.queryByPrimaryKey(id);

        model.addAttribute("data",student);


        FileOutputStream fileOutputStream = new FileOutputStream("c://dd.txt");

        return "showDetail";
    }

    @RequestMapping("/student4")
    public @ResponseBody String queryStudent4(){
        return "mydata";
    }


}
