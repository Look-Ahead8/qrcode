package com.turing.qrcode.controller.service.impl;

import com.turing.qrcode.bean.Student;
import com.turing.qrcode.bean.StudentExample;
import com.turing.qrcode.bean.Table;
import com.turing.qrcode.bean.Time;
import com.turing.qrcode.controller.service.StudentService;
import com.turing.qrcode.dao.StudentMapper;
import com.turing.qrcode.dao.TableMapper;
import com.turing.qrcode.dao.TimeMapper;
import com.turing.qrcode.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Meng
 * @date 2019/9/18
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private TimeMapper timeMapper;

    /**
     * 登录操作
     * 1:判断账户合法性
     * 2:该桌子是否已经被别人坐下
     * 3:坐下桌子
     * 4:记录时间
     *
     * @param student
     * @param tableId
     * @return 0:成功
     * 1:账户密码不对
     * 2:没有该桌子
     * 3:桌子已被人坐下
     * 4:还没有退出的情况下就选择别的椅子坐下
     */
    @Override
    public int login(Student student, int tableId) {
        Student selectStudent = selectByStudentName(student.getStudentName());
        if (selectStudent == null || !selectStudent.getStudentPassword().equals(MD5Util.md5(student.getStudentPassword()))) {
            return 1;
        }
        Table table = selectBytableId(tableId);
        if (table == null) {
            return 2;
        }
        if(selectTimeBystudentId(selectStudent.getStudentId()).size()%2!=0){
            return 4;
        }
        if (table.getState()) {
            return 3;
        }
        Table updateTbale = new Table();
        updateTbale.setTableId(tableId);
        updateTbale.setState(true);
        tableMapper.updateByPrimaryKeySelective(updateTbale);
        Time time = new Time();
        time.setTableId(tableId);
        time.setStudetId(selectStudent.getStudentId());
        timeMapper.insertSelective(time);
        return 0;
    }

    /**
     * 签退
     * 判断桌子是否已经坐下了
     * 判断该学生是否坐了别的桌子
     *
     * @param tableId
     * @return 0处理成功
     * 1为不存在该桌子
     * 2不存在该学生
     * 3该桌子还没人坐下
     * 4签退的桌子被其他人坐下了但不是自己（一般不会出现这种情况）
     */
    @Override
    public int loginout(Integer tableId) {
        Table table = selectBytableId(tableId);
        if (table == null) {
            return 1;
        }
        if (!table.getState()) {
            return 3;
        }
        Integer studentId=timeMapper.selectByTableIdOrder(tableId).getStudetId();
        Student student = selectByStudentId(studentId);
        Time time = new Time();
        time.setTableId(tableId);
        time.setState(1);
        time.setStudetId(studentId);
        timeMapper.insertSelective(time);
        List<Time> times = timeDifference(studentId);
        int count = (int) getTotalTime(times);
        count = count + student.getStudentTime();
        student.setStudentTime(count);
        studentMapper.updateByPrimaryKeySelective(student);
        table.setState(false);
        tableMapper.updateByPrimaryKeySelective(table);
        return 0;
    }

    /**
     * 判断是否被不是自己的其他人坐了
     *
     * @param studentId
     * @param table
     * @return是返回true，否则返回false
     */
    private boolean isOtherSit(Integer studentId, Table table) {
        if (table.getState() && timeMapper.selectByTableIdOrder(table.getTableId()).getStudetId() != studentId) {
            return true;
        }
        return false;
    }


    private long getTotalTime(List<Time> times) {
        Date lastTime = times.get(0).getDatetime();
        Date firstTime = times.get(1).getDatetime();
        return (lastTime.getTime() - firstTime.getTime()) / 1000;
    }

    /**
     * 根据学生姓名查找学生
     *
     * @param studentName
     * @return
     */
    public Student selectByStudentName(String studentName) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentNameEqualTo(studentName);
        List<Student> students = studentMapper.selectByExample(studentExample);
        return students.isEmpty() ? null : students.get(0);
    }

    @Override
    public List<Student> getAllStudentOrderByTime() {
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        studentExample.setOrderByClause("student_time DESC");
        return studentMapper.selectByExample(studentExample);
    }

    public Student selectByStudentId(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    /**
     * 根据主键查找桌子
     *
     * @param tableId
     * @return
     */
    public Table selectBytableId(int tableId) {
        Table table = tableMapper.selectByPrimaryKey(tableId);
        return table;
    }

    /**
     * 在签退时候获取最近签到和签退记录
     */
    public List<Time> timeDifference(Integer studentId) {
        return timeMapper.selectTimeDifference(studentId);
    }

    public List<Time> selectTimeBystudentId(Integer stuentId){
        return timeMapper.selectByStudentId(stuentId);
    }

}
