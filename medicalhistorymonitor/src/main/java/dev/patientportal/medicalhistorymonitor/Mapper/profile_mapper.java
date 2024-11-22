package dev.patientportal.medicalhistorymonitor.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.patientportal.medicalhistorymonitor.Model.profilePage_Model;

public class profile_mapper implements RowMapper {
  

    @Override
    public profilePage_Model mapRow(ResultSet rs, int rowNum) throws SQLException {
        profilePage_Model profile = new profilePage_Model();

        profile.setOptions(rs.getString(("options")));       
        return profile;
    }
}


