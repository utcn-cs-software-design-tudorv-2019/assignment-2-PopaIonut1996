package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.model.dal.dao.Lecture;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentLecture;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.StudentLectureRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherLectureRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class StudentLectureSeed {

    private ApplicationUserRepository applicationUserRepository;
    private StudentLectureRepository studentLectureRepository;
    private TeacherLectureRepository teacherLectureRepository;


    @Transactional
    public void init() {

        StudentLecture studentLecture = new StudentLecture();
        studentLecture.setApplicationUser(applicationUserRepository.getOne(2L));
        studentLecture.setTeacherLecture(teacherLectureRepository.getOne(1L));
        studentLecture.setGrade(6);

        StudentLecture studentLecture1 = new StudentLecture();
        studentLecture1.setApplicationUser(applicationUserRepository.getOne(1L));
        studentLecture1.setTeacherLecture(teacherLectureRepository.getOne(2L));
        studentLecture1.setGrade(9);

        studentLectureRepository.saveAll(Arrays.asList(studentLecture, studentLecture1));
        log.info("Student lecture inserted");





    }
}
