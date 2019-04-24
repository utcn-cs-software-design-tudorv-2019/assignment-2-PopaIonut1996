package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherLecture;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.LectureRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherLectureRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class TeacherLectureSeed {

    private ApplicationUserRepository applicationUserRepository;
    private LectureRepository lectureRepository;
    private TeacherLectureRepository teacherLectureRepository;


    @Transactional
    public void init() {

        TeacherLecture teacherLecture1 = new TeacherLecture();
        teacherLecture1.setApplicationUser(applicationUserRepository.getOne(3L));
        teacherLecture1.setLecture(lectureRepository.getOne(1L));

        TeacherLecture teacherLecture2 = new TeacherLecture();
        teacherLecture2.setApplicationUser(applicationUserRepository.getOne(4L));
        teacherLecture2.setLecture(lectureRepository.getOne(2L));


        teacherLectureRepository.saveAll(Arrays.asList(teacherLecture2, teacherLecture1));
        log.info("Application teacher lectures inserted.");


    }
}
