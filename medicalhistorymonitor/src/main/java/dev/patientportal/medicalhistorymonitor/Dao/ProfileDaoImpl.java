package dev.patientportal.medicalhistorymonitor.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import dev.patientportal.medicalhistorymonitor.Mapper.ProfileMapper;
import dev.patientportal.medicalhistorymonitor.Mapper.RoleMapper;
import dev.patientportal.medicalhistorymonitor.Mapper.UserMapper;
import dev.patientportal.medicalhistorymonitor.Model.ProfileEntity;
import dev.patientportal.medicalhistorymonitor.Model.RoleEntity;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;

@Component
public class ProfileDaoImpl implements ProfileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String Upload(MultipartFile file, Long mobile) throws FileNotFoundException, IOException {
        InputStream stream = file.getInputStream();
        String query = new String("");
        List<ProfileEntity> usr = GetUser(mobile);
        byte[] isImgAvailable = usr.size() != 0 ? usr.getFirst().getPhoto() : null;
        if (isImgAvailable != null) {
            query = "Update UserProfile set Image=?,FileName=? where Mobile=?";
        } else {

            query = "Insert into UserProfile(Image,Mobile,FileName) values(?,?,?)";
        }
        int value = jdbcTemplate.update(query, stream, mobile, file.getOriginalFilename());
        if (value == 0) {
            return new String("Image not uploaded contact system administrator");
        }
        return new String("Image uploaded successfully");
    }

    public List<ProfileEntity> GetUser(Long phone) {
        String query = "Select * from UserProfile where Mobile = ?";
        List<ProfileEntity> value = jdbcTemplate.query(query, new ProfileMapper(), phone);
        return value;
    }

    @Override
    public ProfileEntity insertProfileData(ProfileEntity userProfileEntit) throws FileNotFoundException, IOException {
        String insertQuery = "insert into UserProfile(Gender,Dob,Age,Height,Weight,BloodGroup,Address,MaritialSts,Email,Name,Mobile)values(?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertQuery, userProfileEntit.gender,
                userProfileEntit.dob,
                userProfileEntit.age, userProfileEntit.height,
                userProfileEntit.weight,
                userProfileEntit.blood_group, userProfileEntit.address,
                userProfileEntit.marital_status,
                userProfileEntit.email,
                userProfileEntit.name,
                userProfileEntit.mobile);
        return userProfileEntit;
    }

    @Override
    public ProfileEntity UpdateProfileData(ProfileEntity userProfileEntit) {
        String query = "UPDATE UserProfile SET Name=?, Dob=?,Height=? ,Weight=?,Gender=?,Age=?,BloodGroup=?,Address=?,MaritialSts=?,Email=? WHERE Mobile=?";
        jdbcTemplate.update(query, userProfileEntit.name, userProfileEntit.dob, userProfileEntit.height,
                userProfileEntit.weight, userProfileEntit.gender, userProfileEntit.age, userProfileEntit.blood_group,
                userProfileEntit.address, userProfileEntit.marital_status, userProfileEntit.email,
                userProfileEntit.mobile);
        return userProfileEntit;
    }

    @Override
    public List<UserEntity> GetUserNameByMobile(Long mobile) {
        String query = "Select * from springusers where phone=?";
        List<UserEntity> data = jdbcTemplate.query(query, new UserMapper(), mobile);
        return data;
    }

    @Override
    public List<RoleEntity> GetRoleIdByMobile(Long mobile) {
        String query = "Select * from UserRole where phone=?";
        List<RoleEntity> data = jdbcTemplate.query(query, new RoleMapper(), mobile);
        return data;
    }

    @Override
    public List<Map<String, Object>> BindRole() {
        String query = "Select options from dropdown where dropdownName=?";
        List<Map<String, Object>> option = jdbcTemplate.queryForList(query, "Role");

        return option;
    }

    @Override
    public List<Map<String, Object>> BindSts() {
        String query = "Select options from dropdown where dropdownName=?";
        List<Map<String, Object>> sts = jdbcTemplate.queryForList(query, "Status");
        return sts;
    }

}
