package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherInfo;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.dal.repository.TeacherInfoRepository;
import ro.utcn.assigment.sinu.app.model.service.RoleValidationService;

@RestController
@RequestMapping("/teacher-info")
@AllArgsConstructor
public class TeacherInfoController {

    private ApplicationUserRepository applicationUserRepository;
    private TeacherInfoRepository teacherInfoRepository;
    private RoleValidationService roleValidationService;


    @GetMapping("{applicationUserId}")
    public TeacherInfo getTeacherInformation(@PathVariable Long applicationUserId) {
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.TEACHER.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }
        return teacherInfoRepository.getByApplicationUserId(applicationUserId);
    }

    @PostMapping("{loggedUserId}/{applicationUserId}")
    public void createTeacherInformation(@PathVariable Long loggedUserId, @PathVariable Long applicationUserId, @RequestBody TeacherInfo teacherInfo) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.TEACHER.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }
        teacherInfo.setApplicationUser(applicationUser);
        teacherInfoRepository.save(teacherInfo);
    }

    @DeleteMapping("{loggedUserId}/{teacherInfoId}")
    public void deleteTeacherInformation(@PathVariable Long loggedUserId,@PathVariable Long teacherInfoId) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        TeacherInfo teacherInfo = teacherInfoRepository.getOne(teacherInfoId);
        if (ObjectUtils.isEmpty(teacherInfo)) {
            throw new RuntimeException("teacherInfo is null.");
        }
        teacherInfoRepository.delete(teacherInfo);
    }

    @PutMapping("{loggedUserId}/{applicationUserId}")
    public void updateTeacherInformation(@PathVariable Long loggedUserId,@PathVariable Long applicationUserId, @RequestBody TeacherInfo teacherInfo) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);
        if (ObjectUtils.isEmpty(applicationUser) || !SinuConstants.TEACHER.equals(applicationUser.getRoll())) {
            throw new RuntimeException("applicationUserId is not assigned to a student.");
        }

        TeacherInfo teacherInfoDB = teacherInfoRepository.getOne(teacherInfo.getId());
        if (ObjectUtils.isEmpty(teacherInfo)) {
            throw new RuntimeException("studentInfoId is null.");
        }


        teacherInfoDB.setName(teacherInfo.getName());
        teacherInfoDB.setSurname(teacherInfo.getSurname());
        teacherInfoDB.setCnp(teacherInfo.getCnp());
        teacherInfoDB.setAddress(teacherInfo.getAddress());
        teacherInfoDB.setApplicationUser(applicationUser);
        teacherInfoRepository.save(teacherInfo);
    }

}
