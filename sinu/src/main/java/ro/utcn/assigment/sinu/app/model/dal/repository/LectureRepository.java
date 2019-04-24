package ro.utcn.assigment.sinu.app.model.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.assigment.sinu.app.model.dal.dao.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
