package com.group8.dao;

import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsClassFile;
import com.group8.entity.EtmsItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    EtmsItem findById(int id);

    int update(EtmsItem etmsItem);

    List<EtmsCatalog> findCatalogByTid(int id);

    List<EtmsClassFile> findClassFileByCid(@Param("id") int id, @Param("catalogName") String catalogName);

    String findSchedule(@Param("uid") int uid,@Param("tid") int tid);

    int findClassNum(int tid);
}
