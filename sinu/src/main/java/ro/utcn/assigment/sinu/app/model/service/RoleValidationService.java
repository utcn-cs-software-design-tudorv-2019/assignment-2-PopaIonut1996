package ro.utcn.assigment.sinu.app.model.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ro.utcn.assigment.sinu.app.constant.SinuConstants;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;
import ro.utcn.assigment.sinu.app.model.dal.repository.ApplicationUserRepository;

@Service
@AllArgsConstructor
public class RoleValidationService {
    private ApplicationUserRepository applicationUserRepository;

    private boolean hasRight(Long applicationUserId, String roll) {
        ApplicationUser applicationUser = applicationUserRepository.getOne(applicationUserId);

        if (!ObjectUtils.isEmpty(applicationUser) && applicationUser.getRoll().equals(roll)) {
            return true;
        }
        return false;
    }

    public void userHasRights(Long applicationUserId, String roll) {
        if (!hasRight(applicationUserId, SinuConstants.TEACHER)) {
            throw new RuntimeException("User has no rights");
        }
    }
}
