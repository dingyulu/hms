package com.yjxxt.hms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.hms.base.BaseQuery;
import com.yjxxt.hms.base.BaseService;
import com.yjxxt.hms.bean.Nurse;
import com.yjxxt.hms.bean.NurseUserRole;
import com.yjxxt.hms.mapper.NurseMapper;
import com.yjxxt.hms.mapper.NurseUserRoleMapper;
import com.yjxxt.hms.query.NurseQuery;
import com.yjxxt.hms.utils.AssertUtil;
import com.yjxxt.hms.utils.IDCard;
import com.yjxxt.hms.utils.PhoneUtil;
import com.yjxxt.hms.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.*;

@Service
public class NurseService extends BaseService<Nurse, Integer> {

    @Resource
    private NurseMapper nurseMapper;

    @Resource
    private NurseUserRoleMapper nurseUserRoleMapper;

    /**
     * 多条件分页查询(BaseService 中有对应的方法)
     *
     * @param query
     * @return
     */
    public Map<String, Object> queryNurseByParams(NurseQuery query) {

        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Nurse> nurses = nurseMapper.selectByParams(query);
        for (Nurse n : nurses) {
            if (n.getDesignOne() != null) {
                Nurse nurse = nurseMapper.selectById2(n.getDesignOne());
                System.out.println(nurse.getPname());
                n.setPname(nurse.getPname());
            }
        }
        PageInfo<Nurse> pageInfo = new PageInfo<>(nurses);
        for (Nurse u : pageInfo.getList()
        ) {
            if (u.getDesignOne() != null) {
                u.setState(1);
            }
        }
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加
     *
     * @param nurse
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveNurse(Nurse nurse) {
        //参数校验

        checkParams(nurse);
        //设置相关参数默认值
        //state -->0 代表未分配 1 代表分配
        nurse.setAdmittedDate(new Date());
        nurse.setIsValid(1);
        nurse.setUserPwd(UserIDBase64.encoderUserID(123456));
        //添加
        Integer id = insertHasKey(nurse);
        AssertUtil.isTrue(id == null, "添加失败！");
        //给用户赋予角色
        NurseUserRole nurseUserRole = new NurseUserRole();
        nurseUserRole.setUserId(nurse.getId());
        nurseUserRole.setRoleId(3);
        nurseUserRole.setCreateDate(new Date());
        nurseUserRole.setUpdateDate(new Date());
        AssertUtil.isTrue(nurseUserRoleMapper.insertSelective(nurseUserRole) < 1, "赋予角色失败！");


    }

    /**
     * 数据验证
     *
     * @param nurse
     */
    private void checkParams(Nurse nurse) {
        AssertUtil.isTrue(StringUtils.isBlank(nurse.getUserName()), "请输入姓名！");
        Nurse temp = nurseMapper.selectIdNumber(nurse.getIdNumber());
        //添加操作
//         AssertUtil.isTrue(temp!= null, "身份证已存在，请重新输入!");
        //修改操作
//        System.out.println(nurse.getId());
//        System.out.println(temp.getId());
        AssertUtil.isTrue(StringUtils.isBlank(nurse.getIdNumber()), "请输入身份证！");
        AssertUtil.isTrue(!IDCard.checkIdentityCode(nurse.getIdNumber()), "身份证不合法!");
        AssertUtil.isTrue(temp!=null &&!(nurse.getId().equals(temp.getId())), "身份证已存在，请重新输入!");

        AssertUtil.isTrue(StringUtils.isBlank(nurse.getPhone()), "请输入手机号！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(nurse.getPhone()), "手机号格式不正确！");
    }

    /**
     * 删除记录
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteNurse(Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的数据！");
        // 删除数据
        AssertUtil.isTrue(nurseMapper.deleteBatch(ids) < 0, "数据删除失败！");
    }

    /**
     * 修改
     *
     * @param nurse
     */
    public void updateNurse(Nurse nurse) {
        //参数校验
        Nurse temp = nurseMapper.selectById(nurse.getId());
        AssertUtil.isTrue(temp == null,  "待修改数据不存在！");
        checkParams(nurse);
        temp.setDesignOne(nurse.getDesignOne());
        //设置相关参数
        //未分配
        if (temp.getDesignOne() == null) {
            nurse.setState(1);
        } else {
            nurse.setState(0);
        }
        AssertUtil.isTrue(updateByPrimaryKeySelective(nurse) < 1, "修改失败了！");
    }

   /* public Map<String, Object> queryByParamsForTable(BaseQuery baseQuery) {
        Map<String,Object> result = new HashMap<String,Object>();
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        PageInfo<Nurse> pageInfo =new PageInfo<Nurse>(selectByParams(baseQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }*/

    /**
     * 指派人
     *
     * @return
     */
    public  List<Map<String,Object>>allpatient() {
        List<Map<String,Object>> selectallpatient = nurseMapper.selectallpatient();
        return selectallpatient;
    }
    public  Nurse  selectByPrimaryKey(Integer id){
        Nurse nurse = nurseMapper.selectByPrimaryKey(id);
        return  nurse;
    }

}
