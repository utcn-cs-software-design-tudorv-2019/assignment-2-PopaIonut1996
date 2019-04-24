package ro.utcn.assigment.sinu.app.model.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.assigment.sinu.app.model.dal.dao.StudentLecture;

public interface StudentLectureRepository extends JpaRepository<StudentLecture, Long> {
}
