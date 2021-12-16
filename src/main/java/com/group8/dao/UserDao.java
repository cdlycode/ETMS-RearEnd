package com.group8.dao;

import com.group8.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author acoffee
 * @create 2021-12-14 15:59
 */
public interface UserDao {
    List<EtmsUser> findAllUser();

    EtmsUser findUserById(@Param("id")int id);

    boolean updateUser(EtmsUser etmsUser);

    boolean updatePassword(@Param("id") int id,@Param("newPassword") String newPassword);

    List<EtmsCourse> findCoursesByid(@Param("id")int userid);

    List<EtmsItem> findItemById(@Param("id")int userid);

    List<EtmsUserAm> findAbilityById(@Param("id")int userid);
}
