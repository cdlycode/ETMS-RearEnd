package com.group8.service;

import com.group8.dto.CourseAndItem;
import com.group8.dto.NoJoinItemDto;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsAbilityModel;
import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsUser;

import java.io.IOException;
import java.util.List;

/**
 * @author acoffee
 * @create 2021-12-14 15:56
 */
public interface UserService {
    List<EtmsUser> findAllUser();

    EtmsUser findUserById(int id);

    boolean updateUser( EtmsUser etmsUser);

    boolean updatePassword(int id,String newPassword);

    boolean validatePassword(int id,String oldPassword);

    EtmsUser findUserIndexById(int id);

    List<EtmsUser> findByDeptId(int deptId);

    EtmsUser login(EtmsUser etmsUser);

    String uploadPicture(UploadImg uploadImg) throws IOException;

    List<EtmsUser> findAllStudent(EtmsUser etmsUser);

    int addStudent(EtmsUser etmsUser);

    int deleteStudent(int userId);

    int addCourse(int userId, int courseId);

    int updateStudent(EtmsUser etmsUser);

    EtmsUser getStudentById(int userId);

    List<EtmsAbilityModel> findAmById(int userId);

    EtmsUser getInfo(String token);

    boolean logout(String token);

    CourseAndItem findCourseAndItem(int userId);

    List<EtmsItem> findNoJoinItem(NoJoinItemDto noJoinItemDto);
}
