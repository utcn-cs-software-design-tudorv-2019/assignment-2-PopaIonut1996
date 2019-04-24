package ro.utcn.assigment.sinu.app.model.dal.dao;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser extends BaseEntity {
    private String username, password, roll;

}
