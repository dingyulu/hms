package com.yjxxt.hms.controller;

import com.yjxxt.hms.base.BaseController;
import com.yjxxt.hms.base.ResultInfo;
import com.yjxxt.hms.bean.Nurse;
import com.yjxxt.hms.query.NurseQuery;
import com.yjxxt.hms.service.NurseService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("nurse")
public class NurseController extends BaseController {

    @Autowired
    private NurseService nurseService;

    @RequestMapping("index")
    public String index(){
        return "nurse/nurse";
    }


    /**
     * 多条件分页查询营销机会
     * @param query
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryNurseByParams (NurseQuery query) {
        return nurseService.queryNurseByParams(query);
    }

    /**
     * 添加数据
     * @param nurse
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveNurse(Nurse nurse){
        nurseService.saveNurse(nurse);
        return success("添加数据成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Nurse nurse){
        //添加操作
        System.out.println(nurse);
        nurseService.updateNurse(nurse);
        //返回目标对象
        return success("营销机会数据修改成功！");
    }
    /**
     * 数据添加与更新页面视图转发
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        if(id!=null){
            //查询用户信息
            Nurse nurse = nurseService.selectByPrimaryKey(id);
            //存储
            model.addAttribute("nurse",nurse);
        }
        return "nurse/add_update";
    }

    /**
     * 删除营销机会数据
     * @param ids
     * @return
     */
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deleteNurse (Integer[] ids) {
    // 删除营销机会的数据
        nurseService.deleteNurse(ids);
        return success("数据删除成功了！");
    }
    @RequestMapping("allpatient")
    @ResponseBody
    public  List<Map<String,Object>> allpatient () {
        List<Map<String,Object>> allpatient = nurseService.allpatient();
        return allpatient;
    }
}
