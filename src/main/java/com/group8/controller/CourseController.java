package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.*;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Autowired
    CourseService courseService;

    /*
    * 学生详情页中通过uid查找课程
    * */
    @RequestMapping("/findAllCourse")
    public ResponseEntity<PageInfo<EtmsCourse>> findAllCourse(@RequestBody FormInLine formInLine){
        PageHelper.startPage(formInLine.getPage(),formInLine.getLimit());
        List<EtmsCourse> list = courseService.findAllCourse(formInLine.getId());
        PageInfo<EtmsCourse> pageInfo = new PageInfo(list);
        return new ResponseEntity<>(200,"查询成功",pageInfo);
    }

    /*
     * 我的必修中参加的培训总数
     * */
    @RequestMapping("/findMyRequiredSum/{uid}")
    public ResponseEntity<Integer> findMyCourseSum(@PathVariable int uid){
        int sum =  courseService.findMyCourseSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 我的必修中参加的培训总数
     * */
    @RequestMapping("/findMyElectiveSum/{uid}")
    public ResponseEntity<Integer> findMyElectiveSum(@PathVariable int uid){
        int sum =  courseService.findMyElectiveSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 我的必修中参加的课程展示
     * */
    @RequestMapping("/findAllRequired")
    public ResponseEntity<EtmsCourse> findAllRequired(@RequestBody CourseFindByPage courseFindByPage){
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllRequired(courseFindByPage.getId(),courseFindByPage.getEtmsCourse());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsCoursePageInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /*
     * 我的必修中参加的课程展示
     * */
    @RequestMapping("/findAllElective")
    public ResponseEntity<EtmsCourse> findAllElective(@RequestBody CourseFindByPage courseFindByPage){
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllElective(courseFindByPage.getId(),courseFindByPage.getEtmsCourse());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsCoursePageInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /**
     * 查询所有的课程 包括必修 选修
     * 分页查询 包括关键字查询
     */
    @RequestMapping("/allCourses")
    public ResponseEntity<PageInfo<EtmsCourse>> findAllCourse1(@RequestBody CourseFindByPage courseFindByPage){
        System.out.println("获取的DTO"+courseFindByPage);
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllCourse1(courseFindByPage.getEtmsCourse());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        return new ResponseEntity<PageInfo<EtmsCourse>>(200,"查询成功",etmsCoursePageInfo);
    }

    /**
     *
     * 新增课程
     */
    @RequestMapping("/addCourse")
    public ResponseEntity<EtmsCourse> addCourse(@RequestBody EtmsCourseAbility etmsCourse){
        int i = courseService.addCourse(etmsCourse);
        if (i > 0){
            return new ResponseEntity<>(200, "添加成功");
        }else {
            return new ResponseEntity<>(500, "添加失败");
        }
    }

    /*
    * 课程详情页中通过courseId查找
    * */
    @RequestMapping("/findCourseById/{courseId}")
    public ResponseEntity<EtmsCourse> findCourseById(@PathVariable("courseId") int courseId){
        EtmsCourse course = courseService.findCourseById(courseId);
        return new ResponseEntity(200,"查询成功",course);
    }

    /*
    * 课程详情页中通过课程id查找学员
    * */
    @RequestMapping("/findStudentByCid")
    public ResponseEntity<PageInfo<EtmsUser>> findStudentByCid(@RequestBody FormInLine formInLine){
        int id = formInLine.getId();
        System.out.println(id);
        PageHelper.startPage(formInLine.getPage(),formInLine.getLimit());
        List<EtmsUser> list = courseService.findStudentByCid(formInLine.getId());
        PageInfo<EtmsUser> etmsUserPageInfo = new PageInfo(list);
            return new ResponseEntity(200, "查询成功", etmsUserPageInfo);

    }
    /**
     * 上传封面
     */
    @PostMapping("/uploadCover")
    public ResponseEntity<String> uploadPicture(UploadImg uploadImg) {
        System.out.println(uploadImg);
        String pictureUrl = courseService.uploadPicture(uploadImg);
        return new ResponseEntity<String>(200,"上传成功！",pictureUrl);
    }

    /**
     * 给课程上传视频
     */
    @PostMapping("/uploadVideo")
    public ResponseEntity<String> uploadVideo(UploadFile uploadFile) {
        String videoUrl = courseService.uploadFile(uploadFile);
        return new ResponseEntity<String>(200,"上传成功！",videoUrl);
    }

    /**
     * 删除课程
     */
    @DeleteMapping("deleteCourse/{courseId}")
    public ResponseEntity<EtmsCourse> deleteCourse(@PathVariable int courseId){
        int i = courseService.deleteCourse(courseId);
        if (i > 0){
            return new ResponseEntity<>(200, "删除成功");
        }else {
            return new ResponseEntity<>(500, "删除失败");
        }
    }

    /**
     * 选课中心：热门课程展示 按照课程状态 流行 来排版
     */
    @RequestMapping("/hotCourses")
    public ResponseEntity<EtmsCourse> findHotCourse(){
        List<EtmsCourse> list = courseService.findHotCourses();
        return new ResponseEntity(200,"查询成功！",list);
    }

    /**
     * 选课中心：企业推荐课程 按照课程类型 必修 和创建时间最先 来排版
     */
    @RequestMapping("/companyRecommend")
    public ResponseEntity<EtmsCourse> companyRecommend(){
        List<EtmsCourse> list = courseService.findCompanyRecommend();
        return new ResponseEntity(200,"查询成功！",list);
    }

    /**
     * 打开具体课程页面
     * @param id
     * @return
     */
    @RequestMapping("/openCourse/{id}")
    public ResponseEntity<EtmsCourse> openCourse(@PathVariable int id){
        EtmsCourse etmsCourses = courseService.openCourse(id);
        if (etmsCourses!=null){
            return new ResponseEntity(200, "查询成功！",etmsCourses);
        }else {
            return new ResponseEntity(500, "查询失败！");
        }
    }

    /*
    * 我的选修和必修中根据cid和uid删除课程
    * */
    @RequestMapping("/DeleteCourseByUid")
    public ResponseEntity<String> DeleteCourseByUid(@RequestBody UseridAndCourseid useridAndCourseid){
        int uid = useridAndCourseid.getUid();
        int cid = useridAndCourseid.getCid();
        boolean b = courseService.DeleteCourseByUid(uid,cid);
        if(b){
            return new ResponseEntity(200,"删除成功","删除成功");
        }else{
            return new ResponseEntity(400,"删除失败","");
        }
    }
}
