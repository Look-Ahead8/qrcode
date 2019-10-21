package com.turing.qrcode.dao;

import com.turing.qrcode.bean.Time;
import com.turing.qrcode.bean.TimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TimeMapper {
    long countByExample(TimeExample example);

    int deleteByExample(TimeExample example);

    int deleteByPrimaryKey(Integer timeId);

    int insert(Time record);

    int insertSelective(Time record);

    List<Time> selectByExample(TimeExample example);

    Time selectByPrimaryKey(Integer timeId);

    int updateByExampleSelective(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByExample(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKey(Time record);

    List<Time> selectTimeDifference(Integer studentId);

    List<Time> selectByStudentId(Integer studentId);

    Time selectByTableIdOrder(Integer tableId);

    List<Time> selectTimes();

    Integer selectStudentSitTableId(Integer studentId);
}