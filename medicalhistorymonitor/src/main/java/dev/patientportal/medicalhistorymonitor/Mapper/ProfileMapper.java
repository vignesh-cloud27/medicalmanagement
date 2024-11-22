package dev.patientportal.medicalhistorymonitor.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import dev.patientportal.medicalhistorymonitor.Model.ProfileEntity;

public class ProfileMapper implements RowMapper<ProfileEntity> {

  @Override
  @Nullable
  public ProfileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    ProfileEntity userProfile = new ProfileEntity();

    String email = rs.getString("Email");
    userProfile.setEmail(email);
    float height = rs.getFloat("Height");
    userProfile.setHeight(height);
    float weight = rs.getFloat("Weight");
    userProfile.setWeight(weight);
    String dob = rs.getString("Dob");
    userProfile.setDob(dob);
    String age = rs.getString("Age");
    userProfile.setAge(age);
    String gender = rs.getString("Gender");
    userProfile.setGender(gender);
    String bloodGrp = rs.getString("BloodGroup");
    userProfile.setBloodGrp(bloodGrp);
    String address = rs.getString("Address");
    userProfile.setAddress(address);
    String maritalSts = rs.getString("MaritialSts");
    userProfile.setMaritialsts(maritalSts);
    byte[] photo = rs.getBytes("Image");
    userProfile.setPhoto(photo);
    String fileName=rs.getString("FileName");
    userProfile.setFileName(fileName);

    return userProfile;
  }

}
