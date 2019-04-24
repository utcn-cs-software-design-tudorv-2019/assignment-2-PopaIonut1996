package ro.utcn.assigment.sinu.app.model.dal.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentLecture extends BaseEntity{
    private Integer grade;

    @JoinColumn(name = "APPLICATION_USER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private ApplicationUser applicationUser;

    @JoinColumn(name = "TEACHER_LECTURE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private TeacherLecture teacherLecture;

    @Column(name = "APPLICATION_USER_ID", updatable = false, insertable = false)
    private Long applicationUserId;

    @Column(name = "TEACHER_LECTURE_ID", updatable = false, insertable = false)
    private Long teacherLectureId;


}
