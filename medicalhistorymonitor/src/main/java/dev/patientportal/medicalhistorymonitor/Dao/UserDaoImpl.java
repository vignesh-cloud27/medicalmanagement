package dev.patientportal.medicalhistorymonitor.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.patientportal.medicalhistorymonitor.Mapper.UserMapper;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;

@Component
public class UserDaoImpl implements UserDao<UserEntity> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String CreateUser(UserEntity userEntity) {
        
        String sql = "INSERT INTO SPRINGUSERS (USERNAME,PASSWORD,PHONE) VALUES(?,?,?)";
        int uE = jdbcTemplate.update(sql, userEntity.getName(), userEntity.getPassword(), userEntity.getPhone());
        if (uE == 0) {
            return new String("The User is not created due to technical issue.");
        } else {
            return new String("Thanks for signing up. Your account has been created.");
        }
    }

    @Override
    public List<UserEntity> GetUser(long phone) {
        String sql = "SELECT * FROM SPRINGUSERS WHERE PHONE=?";
        List<UserEntity> user = jdbcTemplate.query(sql, new UserMapper(), phone);
        return user;

    }

    @Override
    public void UpdateUser(long phone) {

    }

    @Override
    public void DeleteUser(long phone) {

    }
    
}
