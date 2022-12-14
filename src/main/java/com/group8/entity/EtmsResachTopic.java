package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * description: EtmsResachTopic <br>
 * date: 2021/12/15 3:51 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

//题目表的实体类
public class EtmsResachTopic {
    private Integer topicId;    //题目id
    private String topicName;  //题目名字
    private Integer demandTableId; //需求表的id
    private String creatorid;   //创建人
    private Date createtime;  //创建人时间
    private String updated;     //修改人
    private Date updatetime;  //修改人时间
    private List<EtmsDemand> etmsDemands;
    private List<EtmsResachAnwer> etmsResachAnwers;
}
