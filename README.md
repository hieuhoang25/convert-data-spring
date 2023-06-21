
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [A good blogs](https://dzone.com/java)
### ===================
1. Process enum in Spring JPA
```java
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
    @Convert(converter = UserRole.UserRoleConverter.class) // convert enum through order number of the elements enum
    @JsonAlias("roleId")
    private UserRole userRole;  // store it under number then when get it , it will be converted to USER Role


    private String name;

    @Enumerated(EnumType.STRING) // use directly string for enum and store it under string type in the db
    private Gender gender;

    @Embedded // embedd the whole attributes of phone class into user
    private Phone phone;
}
```

```java
package com.hicode.convertdata.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Phone {
    private String phoneNumber; // phone_number is belong to user

}

```
```java
    package com.hicode.convertdata.common;

import com.hicode.convertdata.model.User;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum UserRole {
    ADMIN(0, "Admin"),
    USER(1, "User");
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    UserRole(int id, String user) {
        this.id = id;
        this.name = name;
    }
    public static UserRole valueOf(final Integer id){
        if (id == null) {
            return null;
        }
        for (UserRole type : UserRole.values()){
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
    @Converter(autoApply = true)
    public static class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
        
        @Override
        public Integer convertToDatabaseColumn(UserRole attribute) {
            if (attribute == null) {
                throw new RuntimeException("Please provide a valid User Role.");
            }

            return attribute.getId();
        }
        @Override
        public UserRole convertToEntityAttribute(Integer dbData) {
            return UserRole.valueOf(dbData);
        }
    }
}
```
```java
package com.hicode.convertdata.common;

public enum Gender {
    FEMALE, MALE, NO
}
```
***Request***
```json
{         
        "userRole": "USER",
        "name": "hieuhoang",
        "gender": "MALE",
        "phone" : {
            "phoneNumber" : "0776274144"
        }
}
```


2. Schema [Postgres] in Spring JPA
```java
package com.hicode.convertdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tbl_book", schema = "my_schema") // tbl_book table will be stored into my_schema schema
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;
}

```