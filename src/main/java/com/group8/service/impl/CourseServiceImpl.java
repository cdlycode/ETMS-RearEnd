package com.group8.service.impl;

import com.group8.dao.AbilityModelDao;
import com.group8.dao.CourseDao;
import com.group8.dto.AbilityModelSubject;
import com.group8.dto.EtmsCourseAbility;
import com.group8.entity.EtmsCourse;
import com.group8.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired(required = false)
    CourseDao courseDao;
    @Autowired(required = false)
    AbilityModelDao abilityModelDao;

    @Override
    public int findMyCourseSum(int uid) {
        return courseDao.findMyCourseSum(uid);
    }

    @Override
    public List<EtmsCourse> findAllCourse(int user_id) {
        return courseDao.findAllCourse(user_id);
    }

    /**
     * 查询所有的课程包括选修必修
     * @param etmsCourse
     * @return
     */
    @Override
    public List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse) {
        System.out.println("课程："+etmsCourse);
        return courseDao.findAllCourse1(etmsCourse);
    }

    /**
     * 添加课程
     * @param eca
     * @return
     */
    @Override
    public int addCourse(EtmsCourseAbility eca) {

        //添加课程
        EtmsCourse etmsCourse = eca.getEtmsCourse();
        LocalDateTime now = LocalDateTime.now();
        etmsCourse.setCreatedTime(now);
        int i1 = courseDao.addCourse(etmsCourse);
        //获取主键id
        int courseId = etmsCourse.getCourseId();

        //添加能力模型
        List<AbilityModelSubject> list = eca.getAmSubjectLists();
        for (AbilityModelSubject ability:list
        ) {
            ability.setSubjectId(courseId);
        }
        list.get(0).setSubject("course");
        int i2 = abilityModelDao.addOne(list);

        if(i1 >0 && i2 >0){
            return 1;
        }else {
            return 0;
        }

    }
}
