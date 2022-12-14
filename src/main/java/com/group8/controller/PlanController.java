package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.PlanApprove;
import com.group8.dto.PlanDto;
import com.group8.dto.PlanSelect;
import com.group8.entity.EtmsApproveRecord;
import com.group8.entity.EtmsPlan;
import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.PlanService;
import com.group8.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanService planService;

    /**
     * 查询所有培训计划
     * @return
     */
    @RequestMapping("/findAllPlan/{startPage}")
    public ResponseEntity<EtmsPlan> findAllPlan(@PathVariable Integer startPage){
        PageHelper.startPage(startPage,3);
       List<EtmsPlan> etmsPlanList = planService.findAllPlan();
       PageInfo<EtmsPlan> pageInfo = new PageInfo<>(etmsPlanList);
       if(etmsPlanList != null){
           return new ResponseEntity(200,"查询成功",pageInfo);
       } else {
           return new ResponseEntity(400, "查询失败", "服务器维护中");
       }
    }

    /**
     * 查询我发布的计划
     * @param etmsPlan
     * @return
     */
    @RequestMapping("/findMyPlan")
    public ResponseEntity<EtmsPlan> findMyPlan(@RequestBody EtmsPlan etmsPlan){
       List<EtmsPlan> etmsPlanList =  planService.findMyPlan(etmsPlan);
        if(etmsPlanList != null){
            return new ResponseEntity(200,"查询成功",etmsPlanList);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 根据条件查询培训计划
     * @param planSelect
     * @return
     */
    @RequestMapping("/findBySelect")
    public ResponseEntity<EtmsPlan> findBySelect(@RequestBody PlanSelect planSelect){
        System.out.println(planSelect);
        PageHelper.startPage(planSelect.getStartPage(),3);
        List<EtmsPlan> etmsPlanList =  planService.findBySelect(planSelect.getEtmsPlan());
        PageInfo<EtmsPlan> pageInfo = new PageInfo<>(etmsPlanList);
        if(etmsPlanList != null){
            return new ResponseEntity(200,"查询成功",pageInfo);
        }else {
            return new ResponseEntity(400, "查询失败", "未搜索到结果");
        }
    }

    /**
     * 添加计划
     * @param planDto
     * @return
     */
    @RequestMapping("/addPlan")
    public ResponseEntity<EtmsPlan> addPlan(@RequestBody PlanDto planDto){

        System.out.println(planDto.getApproveRecords().get(0).getApproveEndtime());
        int i =  planService.addPlan(planDto);
        if(i >= 3){
            return new ResponseEntity(200,"新增成功",i);
        }else {
              return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 查询我的进度查询
     * @param etmsPlan
     * @return
     */
    @RequestMapping("/findPlanSchedule")
    public ResponseEntity<EtmsPlan> findPlanSchedule(@RequestBody EtmsPlan etmsPlan){
        List<EtmsPlan> etmsPlanList =  planService.findPlanSchedule(etmsPlan);
        if(etmsPlanList != null){
            return new ResponseEntity(200,"查询成功",etmsPlanList);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 查询我的待审批计划
     * @param planApprove
     * @return
     */
    @RequestMapping("/findMyApproveNow")
    public ResponseEntity<EtmsPlan> findMyApprove(@RequestBody PlanApprove planApprove){
        PageHelper.startPage(planApprove.getStartPage(),5);
        List<EtmsPlan> etmsPlanList =  planService.findMyApprove(planApprove);
        PageInfo pageInfo = new PageInfo(etmsPlanList);
        if(etmsPlanList != null){
            return new ResponseEntity(200,"查询成功",pageInfo);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 查询我已经审核的计划
     * @param planApprove
     * @return
     */
    @RequestMapping("/findMyApproved")
    public ResponseEntity<EtmsPlan> findMyApproved(@RequestBody PlanApprove planApprove){
        PageHelper.startPage(planApprove.getStartPage(),5);
        List<EtmsPlan> etmsPlanList =  planService.findMyApproved(planApprove);
        PageInfo pageInfo = new PageInfo(etmsPlanList);
        if(etmsPlanList != null){
            return new ResponseEntity(200,"查询成功",pageInfo);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 查询计划详情
     * @param pid
     * @return
     */
    @RequestMapping("/findPlanById/{pid}")
    public ResponseEntity<PlanDto> findPlanById(@PathVariable Integer pid){
        EtmsPlan etmsPlan =  planService.findPlanById(pid);
        System.out.println(etmsPlan);
        if(etmsPlan != null){
            return new ResponseEntity(200,"查询成功", etmsPlan);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file){
        String url = UploadUtils.uploadUtils(file);
        System.out.println(url);
        if(url != null){
            return new ResponseEntity(200,"查询成功",url);
        }else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 找审核人
     * @param etmsApproveRecordList
     * @return
     */
    @RequestMapping("/findUser")
    public ResponseEntity<EtmsUser> findUser(@RequestBody List<EtmsApproveRecord> etmsApproveRecordList) {
        List<EtmsUser> etmsUsers = planService.findUser(etmsApproveRecordList);
        if (etmsUsers != null) {
            return new ResponseEntity(200, "查询成功", etmsUsers);
        } else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }

    /**
     * 审核通过
     * @param pid
     * @return
     */
    @RequestMapping("/approvePass/{pid}")
    public ResponseEntity<EtmsUser> approvePass(@PathVariable Integer pid) {
        int i = planService.updateApprovePass(pid);
        if (i == 1) {
            return new ResponseEntity(200, "查询成功", i);
        } else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }
    /**
     * 审核未通过
     * @param pid
     * @return
     */
    @RequestMapping("/approveNopass/{pid}")
    public ResponseEntity<EtmsUser> approveNopass(@PathVariable Integer pid) {
        int i = planService.updateApproveNopass(pid);
        if (i == 1) {
            return new ResponseEntity(200, "查询成功", i);
        } else {
            return new ResponseEntity(400, "查询失败", "服务器维护中");
        }
    }
}
