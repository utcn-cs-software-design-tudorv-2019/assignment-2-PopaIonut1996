package ro.utcn.assigment.sinu.seed;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentInfo;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.StudentInfoRepository;

import java.util.Arrays;

@Service
@AllArgsConstructor
@Log4j2
public class StudentInfoSeed {

    private StudentInfoRepository studentInfoRepository;
    private ApplicationUserRepository applicationUserRepository;

    @Transactional
    public void init() {
        ApplicationUser applicationUserStudent1 = applicationUserRepository.getOne(1L);

        StudentInfo applicationUserStudentInfo1 = new StudentInfo();
        applicationUserStudentInfo1.setApplicationUser(applicationUserStudent1);
        applicationUserStudentInfo1.setAddress("address student 1");
        applicationUserStudentInfo1.setCnp("123456798");
        applicationUserStudentInfo1.setIcn("123456798");
        applicationUserStudentInfo1.setName("student1");

        ApplicationUser applicationUserStudent2 = applicationUserRepository.getOne(2L);

        StudentInfo applicationUserStudentInfo2 = new StudentInfo();
        applicationUserStudentInfo2.setApplicationUser(applicationUserStudent2);
        applicationUserStudentInfo2.setAddress("address student 2");
        applicationUserStudentInfo2.setCnp("987654321");
        applicationUserStudentInfo2.setIcn("987654321");
        applicationUserStudentInfo2.setName("student2");


        studentInfoRepository.saveAll(Arrays.asList(applicationUserStudentInfo1, applicationUserStudentInfo2));
        log.info("Application student info inserted.");


    }
}
