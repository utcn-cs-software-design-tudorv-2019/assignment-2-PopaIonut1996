package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.Lecture;
import ro.utcn.assigment.sinu.app.model.dal.repository.LectureRepository;
import ro.utcn.assigment.sinu.app.model.service.RoleValidationService;

@RestController
@RequestMapping("/lecture")
@AllArgsConstructor
public class LectureController {

    private LectureRepository lectureRepository;
    private RoleValidationService roleValidationService;

    @GetMapping("{id}")
    public Lecture getLecture(@PathVariable Long id) {
        return lectureRepository.getOne(id);
    }

    @DeleteMapping("{id}")
    public void deleteLecture(@PathVariable Long id) {
        lectureRepository.deleteById(id);
    }

    @PostMapping({"loggedUserId"})
    public void createLecture(@PathVariable Long loggedUserId,@RequestBody Lecture lecture) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        if (!ObjectUtils.isEmpty(lecture.getId())) {
            throw new RuntimeException("Lecture id is not null");
        }
        lectureRepository.save(lecture);

    }

    @PutMapping({"loggedUserId"})
    public void updateLecture(@PathVariable Long loggedUserId,@RequestBody Lecture lecture) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        Lecture lecture1 = lectureRepository.getOne(lecture.getId());
        if (ObjectUtils.isEmpty(lecture1)) {
            throw new RuntimeException("Lecture not found");
        }
        lectureRepository.save(lecture);
    }

}
