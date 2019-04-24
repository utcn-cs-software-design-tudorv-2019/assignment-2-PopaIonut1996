package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.Lecture;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherInfo;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.LectureRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherInfoRepository;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class LectureSeed {

    private LectureRepository lectureRepository;


    @Transactional
    public void init() {


        Lecture lecture1=new Lecture();
        lecture1.setName("Mathematics");
        lecture1.setProfile("Real");

        Lecture lecture2=new Lecture();
        lecture2.setName("Current Language");
        lecture2.setProfile("HR");


        lectureRepository.saveAll(Arrays.asList(lecture1,lecture2));
        log.info("Application lectures inserted.");


    }
}
