package dev.patientportal.medicalhistorymonitor.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import dev.patientportal.medicalhistorymonitor.Model.RoleEntity;

public class RoleMapper implements RowMapper<RoleEntity> {
    

    @Override
    @Nullable
    public RoleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoleEntity roleEnt = new RoleEntity();
        roleEnt.setRoleId(rs.getInt("roleId"));
        roleEnt.setRoleName(rs.getString("roleName"));
        roleEnt.setPhone(rs.getLong("phone"));
        return roleEnt;
    }

}


    