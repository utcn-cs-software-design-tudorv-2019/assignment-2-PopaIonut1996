package ro.utcn.assigment.sinu.app.model.dal.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeacherInfo extends BaseEntity {
    private String name,cnp,address,surname;

    @OneToOne
    private ApplicationUser applicationUser;
}
