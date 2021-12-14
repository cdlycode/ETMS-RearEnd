package com.group8.controller;

import com.group8.entity.EtmsDemand;
import com.group8.entity.ResponseEntity;
import com.group8.service.EtmsDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EtmsDemandController {
    @Autowired
    EtmsDemandService etmsDemandService;

    //查找所有的需求
    @RequestMapping("/findAllDemand")
    public ResponseEntity findAllDemand(){
        List<EtmsDemand> etmsDemandList = etmsDemandService.findAllDemand();
    return  new ResponseEntity(200,"查询成功",etmsDemandList);
    }

    //查找个人发布的需求
    @RequestMapping ("/findMyDemand/{uid}")
    public ResponseEntity findMyDemand(@PathVariable Integer uid){
        List<EtmsDemand> etmsDemandList = etmsDemandService.findMyDemand(uid);
        return  new ResponseEntity(200,"查询成功",etmsDemandList);
    }
    //查询具体发布需求
    @RequestMapping ("/findDemandByid/{did}")
    public ResponseEntity findDemandByid(@PathVariable Integer did){
        EtmsDemand etmsDemand = etmsDemandService.findDemandByid(did);
        return  new ResponseEntity(200,"查询成功",etmsDemand);
    }
    //新增需求发布
    @RequestMapping("/addDemand")
    public ResponseEntity addDemand(@RequestBody EtmsDemand etmsDemand ){
        int i = etmsDemandService.addDemand(etmsDemand);
        if(i == 1){
            return  new ResponseEntity(200,"查询成功",i);
        } else {
            return  new ResponseEntity(401,"查询失败",i);
        }

    }
}
