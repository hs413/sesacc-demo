package sesac.sesaccdemo.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
public class Manager {

    @Id
    @Column
    private Long id;

    private String campus_name;

    private String address;

}
