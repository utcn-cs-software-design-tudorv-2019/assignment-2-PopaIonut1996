package ro.utcn.assigment.sinu.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;
import ro.utcn.assigment.sinu.app.model.service.RoleValidationService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class ApplicationUserController {

    private ApplicationUserRepository applicationUserRepository;
    private RoleValidationService roleValidationService;

    @GetMapping("{id}")
    public ApplicationUser getApplicationUser(@PathVariable Long id) {
        return applicationUserRepository.getOne(id);
    }

    @DeleteMapping("{loggedUserId}/{id}")
    public void deleteApplicationUser(@PathVariable Long loggedUserId, @PathVariable Long id) {
        roleValidationService.userHasRights(loggedUserId, SinuConstants.TEACHER);
        applicationUserRepository.deleteById(id);
    }

    @PostMapping
    public void createApplicationUser(@RequestBody ApplicationUser applicationUser) {
        if (!ObjectUtils.isEmpty(applicationUser.getId())) {
            throw new RuntimeException("Application User id is not null");
        }
        applicationUserRepository.save(applicationUser);

    }

    @PutMapping
    public void updateApplicationUser(@RequestBody ApplicationUser applicationUser) {
        ApplicationUser applicationUser1 = applicationUserRepository.getOne(applicationUser.getId());
        if (ObjectUtils.isEmpty(applicationUser1)) {
            throw new RuntimeException("Application User not found");
        }
        applicationUserRepository.save(applicationUser);
    }

}
