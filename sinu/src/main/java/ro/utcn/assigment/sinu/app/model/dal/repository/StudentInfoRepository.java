package ro.utcn.assigment.sinu.app.model.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentInfo;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

    StudentInfo getByApplicationUserId(Long applicationUserId);
}
