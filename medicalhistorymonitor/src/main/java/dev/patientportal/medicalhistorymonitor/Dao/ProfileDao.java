package dev.patientportal.medicalhistorymonitor.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import dev.patientportal.medicalhistorymonitor.Model.ProfileEntity;
import dev.patientportal.medicalhistorymonitor.Model.RoleEntity;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;

public interface ProfileDao {

    String Upload(MultipartFile file, Long mobile) throws FileNotFoundException, IOException;

    List<ProfileEntity> GetUser(Long mobile);

    ProfileEntity insertProfileData(ProfileEntity userProfileEntit) throws FileNotFoundException, IOException;

    ProfileEntity UpdateProfileData(ProfileEntity userProfileEntit); 

    List<UserEntity> GetUserNameByMobile(Long mobile);

    List<RoleEntity> GetRoleIdByMobile(Long mobile);

    List<Map<String, Object>> BindRole();

    List<Map<String, Object>> BindSts();

}
