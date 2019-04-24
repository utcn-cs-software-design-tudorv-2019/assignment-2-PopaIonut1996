package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentLecture;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherLecture;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.StudentLectureRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherLectureRepository;

@RestController
@RequestMapping("/student-lecture")
@AllArgsConstructor
public class StudentLectureController {

    private StudentLectureRepository studentLectureRepository;
    private ApplicationUserRepository applicationUserRepository;
    private TeacherLectureRepository teacherLectureRepository;

    @GetMapping("{id}")
    public StudentLecture getstudentLecture(@PathVariable Long id) {
        return studentLectureRepository.getOne(id);
    }

    @DeleteMapping("{id}")
    public void deletestudentLecture(@PathVariable Long id) {
        studentLectureRepository.deleteById(id);
    }

    @PostMapping
    public void createstudentLecture(@RequestBody StudentLecture studentLecture) {
        if (!ObjectUtils.isEmpty(studentLecture.getId())) {
            throw new RuntimeException("Student Lecture id is not null");
        }
        TeacherLecture teacherLecture = teacherLectureRepository.getOne(studentLecture.getTeacherLectureId());
        if (ObjectUtils.isEmpty(teacherLecture)) {
            throw new RuntimeException("Teacher Lecture id invalid");
        }

        ApplicationUser applicationUser = applicationUserRepository.getOne(studentLecture.getApplicationUserId());
        if (ObjectUtils.isEmpty(applicationUser) && SinuConstants.STUDENT.equals(applicationUser.getRoll())) {
            throw new RuntimeException("Application user id invalid");
        }

        studentLecture.setTeacherLecture(teacherLecture);
        studentLecture.setApplicationUser(applicationUser);

        studentLectureRepository.save(studentLecture);

    }

    @PutMapping
    public void updatestudentLecture(@RequestBody StudentLecture studentLecture) {
        StudentLecture studentLecture1 = studentLectureRepository.getOne(studentLecture.getId());
        if (!ObjectUtils.isEmpty(studentLecture1.getId())) {
            throw new RuntimeException("Student lecture is not null");
        }
        TeacherLecture teacherLecture = teacherLectureRepository.getOne(studentLecture.getTeacherLectureId());
        if (ObjectUtils.isEmpty(teacherLecture)) {
            throw new RuntimeException("Teacher Lecture id invalid");
        }
        ApplicationUser applicationUser = applicationUserRepository.getOne(studentLecture.getApplicationUserId());
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.STUDENT.equals(applicationUser.getRoll())) {
            throw new RuntimeException("Application user is invalid");
        }
        studentLecture1.setApplicationUser(applicationUser);
        studentLecture1.setTeacherLecture(teacherLecture);
        studentLectureRepository.save(studentLecture1);
    }

}
