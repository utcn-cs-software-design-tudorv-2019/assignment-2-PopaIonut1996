package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class ApplicationUserSeed {

    private ApplicationUserRepository applicationUserRepository;

    @Transactional
    public void init() {
        ApplicationUser applicationUserStudent1 = new ApplicationUser();
        applicationUserStudent1.setUsername("student1");
        applicationUserStudent1.setPassword("student1");
        applicationUserStudent1.setRoll(SinuConstants.STUDENT);
        ApplicationUser applicationUserStudent2 = new ApplicationUser();
        applicationUserStudent2.setUsername("student2");
        applicationUserStudent2.setPassword("student2");
        applicationUserStudent2.setRoll(SinuConstants.STUDENT);
        ApplicationUser applicationUserTeacher1 = new ApplicationUser();
        applicationUserTeacher1.setUsername("teacher1");
        applicationUserTeacher1.setPassword("teacher1");
        applicationUserTeacher1.setRoll(SinuConstants.TEACHER);
        ApplicationUser applicationUserTeacher2 = new ApplicationUser();
        applicationUserTeacher2.setUsername("teacher2");
        applicationUserTeacher2.setPassword("teacher2");
        applicationUserTeacher2.setRoll(SinuConstants.TEACHER);

        applicationUserRepository.saveAll(Arrays.asList(applicationUserStudent1, applicationUserStudent2, applicationUserTeacher1,applicationUserTeacher2));
        log.info("Application user inserted.");


    }
}
