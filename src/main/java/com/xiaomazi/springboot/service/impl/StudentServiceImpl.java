package com.xiaomazi.springboot.service.impl;

import com.xiaomazi.springboot.mapper.StudentMapper;
import com.xiaomazi.springboot.model.Student;
import com.xiaomazi.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: 15-springboot-Advanced
 * @description
 * @author: XiaoMaZi
 * @create: 2021-05-08 14:35
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
