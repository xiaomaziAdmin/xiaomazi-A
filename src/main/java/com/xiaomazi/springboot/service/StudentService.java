package com.xiaomazi.springboot.service;

import com.xiaomazi.springboot.model.Student;

/**
 * @program: 15-springboot-Advanced
 * @description
 * @author: XiaoMaZi
 * @create: 2021-05-08 14:35
 **/
public interface StudentService {
    Student queryByPrimaryKey(Integer id);
}
