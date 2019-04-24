package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentInfo;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherInfo;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.StudentInfoRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherInfoRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class TeacherInfoSeed {

    private TeacherInfoRepository teacherInfoRepository;
    private ApplicationUserRepository applicationUserRepository;

    @Transactional
    public void init() {
        ApplicationUser applicationUserTeacher1 = applicationUserRepository.getOne(3L);

        TeacherInfo applicationUserTeacherInfo1 = new TeacherInfo();
        applicationUserTeacherInfo1.setApplicationUser(applicationUserTeacher1);
        applicationUserTeacherInfo1.setAddress("address teacher 1");
        applicationUserTeacherInfo1.setCnp("123456798");
        applicationUserTeacherInfo1.setSurname("SurnameTeacher1");
        applicationUserTeacherInfo1.setName("NameTeacher1");
        ApplicationUser applicationUserTeacher2 =applicationUserRepository.getOne(4L);

        TeacherInfo applicationUserTeacherInfo2=new TeacherInfo();
        applicationUserTeacherInfo2.setApplicationUser(applicationUserTeacher2);
        applicationUserTeacherInfo2.setSurname("SurnameTeacher2");
        applicationUserTeacherInfo2.setName("NameTeacher2");
        applicationUserTeacherInfo2.setAddress("AddressTeacher2");
        applicationUserTeacherInfo2.setCnp("1234352467");




        teacherInfoRepository.saveAll(Arrays.asList(applicationUserTeacherInfo1,applicationUserTeacherInfo2));
        log.info("Application teachers info inserted.");


    }
}
