package com.group8.dao;

import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsUser;
import com.group8.entity.EtmsUserAm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<EtmsUser> findByDeptId(int deptId);

    List<EtmsUser> findAllUser();

    EtmsUser findUserById(@Param("id")int id);

    boolean updateUser(EtmsUser etmsUser);

    boolean updatePassword(@Param("id") int id,@Param("newPassword") String newPassword);

    List<EtmsCourse> findCoursesByid(@Param("id")int userid);

    List<EtmsItem> findItemById(@Param("id")int userid);

    List<EtmsUserAm> findAbilityById(@Param("id")int userid);

    EtmsUser findByUsernamAndPassword(@Param("username") String userName, @Param("password") String userPassword);

    String validatePassword(int id);

    boolean uploadPicture(@Param("headImg") String headImg,@Param("userId") int userId);

    List<EtmsUser> findAllStudent(EtmsUser etmsUser);

    List<EtmsUser> checkUser(EtmsUser etmsUser);

    int addStudent(EtmsUser etmsUser);

    int deleteStudent(int userId);

    int updateStudent(EtmsUser etmsUser);

    EtmsUser getStudentById(int userId);


    int addCourse(@Param("userId") int userId,@Param("courseId") int courseId);

    Integer findUidCid(@Param("userId") int userId,@Param("courseId") int courseId);
}
