package dev.patientportal.medicalhistorymonitor.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;

public class UserMapper implements RowMapper<UserEntity> {

    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        UserEntity userEnt = new UserEntity();
        userEnt.setName(rs.getString("username"));
        userEnt.setPassword(rs.getString("password"));
        userEnt.setPhone(rs.getLong("phone"));
        return userEnt;
    }

}
