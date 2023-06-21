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

        /**
         * Converts the value stored in the entity attribute into the
         * data representation to be stored in the database.
         *
         * @param attribute the entity attribute value to be converted
         * @return the converted data to be stored in the database
         * column
         */
        @Override
        public Integer convertToDatabaseColumn(UserRole attribute) {
            if (attribute == null) {
                throw new RuntimeException("Please provide a valid User Role.");
            }

            return attribute.getId();
        }

        /**
         * Converts the data stored in the database column into the
         * value to be stored in the entity attribute.
         * Note that it is the responsibility of the converter writer to
         * specify the correct <code>dbData</code> type for the corresponding
         * column for use by the JDBC driver: i.e., persistence providers are
         * not expected to do such type conversion.
         *
         * @param dbData the data from the database column to be
         *               converted
         * @return the converted value to be stored in the entity
         * attribute
         */
        @Override
        public UserRole convertToEntityAttribute(Integer dbData) {
            return UserRole.valueOf(dbData);
        }
    }
}
