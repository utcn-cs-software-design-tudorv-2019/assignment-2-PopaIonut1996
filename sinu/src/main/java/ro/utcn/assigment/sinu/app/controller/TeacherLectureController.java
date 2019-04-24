package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.Lecture;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherLecture;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.LectureRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherLectureRepository;
import ro.utcn.assigment.sinu.app.model.service.RoleValidationService;

@RestController
@RequestMapping("/teacher-lecture")
@AllArgsConstructor
public class TeacherLectureController {

    private TeacherLectureRepository teacherLectureRepository;
    private LectureRepository lectureRepository;
    private ApplicationUserRepository applicationUserRepository;
    private RoleValidationService roleValidationService;

    @GetMapping("{id}")
    public TeacherLecture getteacherLecture(@PathVariable Long id) {
        return teacherLectureRepository.getOne(id);
    }

    @DeleteMapping("{loggedUserId}/{id}")
    public void deleteteacherLecture(@PathVariable Long loggedUserId,@PathVariable Long id) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        teacherLectureRepository.deleteById(id);
    }

    @PostMapping("{loggedUserId}")
    public void createteacherLecture(@PathVariable Long loggedUserId,@RequestBody TeacherLecture teacherLecture) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        if (!ObjectUtils.isEmpty(teacherLecture.getId())) {
            throw new RuntimeException("Teacher Lecture id is not null");
        }
        Lecture lecture = lectureRepository.getOne(teacherLecture.getLectureId());
        if (ObjectUtils.isEmpty(lecture)) {
            throw new RuntimeException("Lecture id invalid");
        }

        ApplicationUser applicationUser = applicationUserRepository.getOne(teacherLecture.getApplicationUserId());
        if (ObjectUtils.isEmpty(applicationUser) && SinuConstants.TEACHER.equals(applicationUser.getRoll())) {
            throw new RuntimeException("Application user id invalid");
        }

        teacherLecture.setLecture(lecture);
        teacherLecture.setApplicationUser(applicationUser);

        teacherLectureRepository.save(teacherLecture);

    }

    @PutMapping("{loggedUserId}")
    public void updateteacherLecture(@PathVariable Long loggedUserId,@RequestBody TeacherLecture teacherLecture) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        TeacherLecture teacherLecture1 = teacherLectureRepository.getOne(teacherLecture.getId());
        if (!ObjectUtils.isEmpty(teacherLecture1.getId())) {
            throw new RuntimeException("Teacher lecture is not null");
        }
        Lecture lecture=lectureRepository.getOne(teacherLecture.getLectureId());
        if(ObjectUtils.isEmpty(lecture))
        {
            throw new RuntimeException("Lecture id invalid");
        }
        ApplicationUser applicationUser=applicationUserRepository.getOne(teacherLecture.getApplicationUserId());
        if(ObjectUtils.isEmpty(applicationUser)||!SinuConstants.TEACHER.equals(applicationUser.getRoll()))
        {
            throw new RuntimeException("Application user is invalid");
        }
        teacherLecture1.setApplicationUser(applicationUser);
        teacherLecture1.setLecture(lecture);
        teacherLectureRepository.save(teacherLecture1);
    }

}
