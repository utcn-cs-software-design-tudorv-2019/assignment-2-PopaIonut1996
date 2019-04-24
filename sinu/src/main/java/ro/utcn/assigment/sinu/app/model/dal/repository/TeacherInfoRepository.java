package ro.utcn.assigment.sinu.app.model.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.assigment.sinu.app.model.dal.dao.TeacherInfo;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Long> {
    TeacherInfo getByApplicationUserId(Long applicationUserId);
}
