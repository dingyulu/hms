package com.yjxxt.hms.mapper;

import com.yjxxt.hms.base.BaseMapper;
import com.yjxxt.hms.bean.Nurse;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface NurseMapper extends BaseMapper<Nurse,Integer> {

    Nurse selectById2(Integer id);
    Nurse selectById(Integer id);

    List<Map<String,Object>>selectallpatient();


    Nurse selectIdNumber(String idNumber);
}