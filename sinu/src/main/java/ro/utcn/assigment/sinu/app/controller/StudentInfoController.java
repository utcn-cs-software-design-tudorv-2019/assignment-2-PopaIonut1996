package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentInfo;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.StudentInfoRepository;

@RestController
@RequestMapping("/student-info")
@AllArgsConstructor
public class StudentInfoController {

    private ApplicationUserRepository applicationUserRepository;
    private StudentInfoRepository studentInfoRepository;

    @GetMapping("{applicationUserId}")
    public StudentInfo getStudentInformation(@PathVariable Long applicationUserId) {
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.STUDENT.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }
        return studentInfoRepository.getByApplicationUserId(applicationUserId);
    }

    @PostMapping("{applicationUserId}")
    public void createStudentInformation(@PathVariable Long applicationUserId, @RequestBody StudentInfo studentInfo) {
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.STUDENT.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }
        studentInfo.setApplicationUser(applicationUser);
        studentInfoRepository.save(studentInfo);
    }

    @DeleteMapping("{studentInfoId}")
    public void deleteStudentInformation(@PathVariable Long studentInfoId) {
        StudentInfo studentInfo = studentInfoRepository.getOne(studentInfoId);
        if (ObjectUtils.isEmpty(studentInfo)) {
            throw new RuntimeException("studentInfoId is null.");
        }
        studentInfoRepository.delete(studentInfo);
    }

    @PutMapping("{applicationUserId}")
    public void updateStudentInformation(@PathVariable Long applicationUserId, @RequestBody StudentInfo studentInfo) {
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.STUDENT.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }

        StudentInfo studentInfoDB = studentInfoRepository.getOne(studentInfo.getId());
        if (ObjectUtils.isEmpty(studentInfo)) {
            throw new RuntimeException("studentInfoId is null.");
        }

        studentInfoDB.setName(studentInfo.getName());
        studentInfoDB.setIcn(studentInfo.getIcn());
        studentInfoDB.setCnp(studentInfo.getCnp());
        studentInfoDB.setAddress(studentInfo.getAddress());
        studentInfoDB.setApplicationUser(applicationUser);
        studentInfoRepository.save(studentInfo);
    }

}
