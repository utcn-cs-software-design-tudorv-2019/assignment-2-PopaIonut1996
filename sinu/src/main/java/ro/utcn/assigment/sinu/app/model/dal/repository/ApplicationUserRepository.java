package ro.utcn.assigment.sinu.app.model.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.assigment.sinu.app.model.dal.dao.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
}
