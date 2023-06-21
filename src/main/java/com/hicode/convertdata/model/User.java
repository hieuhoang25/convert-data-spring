package com.hicode.convertdata.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hicode.convertdata.common.Gender;
import com.hicode.convertdata.common.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "tblUser")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name="role")
    @Convert(converter = UserRole.UserRoleConverter.class)
    @JsonAlias("roleId")
    private UserRole userRole;


    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Phone phone;
}
