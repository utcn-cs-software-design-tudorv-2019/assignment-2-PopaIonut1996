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
public class StudentInfo extends BaseEntity {
    private String name,cnp,address,icn;

    @OneToOne
    private ApplicationUser applicationUser;

}
