package ro.utcn.assigment.sinu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentLecture;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherLecture;
import ro.utcn.assigment.sinu.seed.*;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class PostConstructSeed {

    private ApplicationUserSeed applicationUserSeed;
    private StudentInfoSeed studentInfoSeed;
    private TeacherInfoSeed teacherInfoSeed;
    private LectureSeed lectureSeed;
    private StudentLectureSeed studentLectureSeed;
    private TeacherLectureSeed teacherLectureSeed;

    @PostConstruct
    public void init() {
        applicationUserSeed.init();
        studentInfoSeed.init();
        teacherInfoSeed.init();
        lectureSeed.init();
        teacherLectureSeed.init();
        studentLectureSeed.init();
    }
}
