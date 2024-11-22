package dev.patientportal.medicalhistorymonitor.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import dev.patientportal.medicalhistorymonitor.Dao.ProfileDao;
import dev.patientportal.medicalhistorymonitor.Model.ProfileEntity;
import dev.patientportal.medicalhistorymonitor.Model.RoleEntity;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;
import jakarta.servlet.http.HttpSession;

@Service
public class ProfileService {
    private static ProfileDao prflDao;
    public static Dictionary<String, String> ProfileData = new Hashtable<>();
    static List<ProfileEntity> prflDetail = Collections.<ProfileEntity>emptyList();
    static ProfileEntity profileEntity = new ProfileEntity();

    public ProfileService(ProfileDao prDAo) {
        this.prflDao = prDAo;
    }

    public static Object BindData(Long mobile, HttpSession session,Model model) throws FileNotFoundException, IOException {
        List<UserEntity> data = prflDao.GetUserNameByMobile(mobile);
        prflDetail = prflDao.GetUser(mobile);
        MultipartFile file = null;
        if (prflDetail.size() != 0) {
            for (ProfileEntity proEntity : prflDetail) {
                profileEntity = proEntity;
            }
            if (profileEntity.getFilePath() == null) {
                Path val = UploadProfileImage(file, mobile);
                profileEntity.setFilePath(val.toString());
            }
            
            }
            if (profileEntity.name == null && profileEntity.mobile == null || data.getFirst().name != profileEntity.name) {
                for (UserEntity userEntity : data) {
                    profileEntity.setName(userEntity.name);
                    profileEntity.setMobile(mobile);
                }
        }
        if((profileEntity.name.equals("Hospital"))&& profileEntity.getRole() == null){           
            profileEntity.setRole(BindUserRole());
            model.addAttribute("Role", profileEntity.getRole());
        }
        if( profileEntity.status == null){
            profileEntity.setStatus(BindStatus());
            model.addAttribute("Status", profileEntity.getStatus());
        }
     
        session.setAttribute("ProfileEntity", profileEntity);
        return session.getAttribute("ProfileEntity");
    } 

    public static Path UploadProfileImage(MultipartFile file, Long Mobile)
            throws FileNotFoundException, IOException {       
        prflDetail = prflDao.GetUser(Mobile);
        String fileName = file != null ? file.getOriginalFilename() : prflDetail.getFirst().getFileName();
        String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/static/img/profile";
        Path actualPath = Paths.get(path.substring(path.lastIndexOf('/', (path.lastIndexOf('/')) - 1), path.length())
                + "/" + fileName);
        boolean isFileExist = false;
        for (final File fileEntry : new File(path).listFiles()) {
            var x = fileEntry.getName();
            if (fileEntry.getName().equals(fileName)) {
                isFileExist = true;
            }
        }

        byte[] img = prflDetail.size() != 0 ? prflDetail.getFirst().getPhoto() : null;
        if (img != null && isFileExist) {
            ProfileData.put("image", actualPath.toString());
            return actualPath;
        } else {
            String respString = prflDao.Upload(file, Mobile);
            if (respString.contains("successfully")) {
                prflDetail = prflDao.GetUser(Mobile);
                byte[] imgBytes = prflDetail.getFirst().getPhoto();
                BufferedImage imgInptStr = ImageIO.read(new ByteArrayInputStream(imgBytes));
                ImageIO.write(imgInptStr, "jpg", new File(path, fileName));
                ProfileData.put("image", actualPath.toString());
                return actualPath;
            }
        }
        ProfileData.put("image", "");
        return Paths.get("");

    }
    
    public static ProfileEntity AddUser(ProfileEntity prEnt) throws FileNotFoundException, IOException {
        List<ProfileEntity> profileEntity;
        profileEntity = prflDao.GetUser(prEnt.mobile);
        if (profileEntity.size() != 0) {
            return prflDao.UpdateProfileData(prEnt);
        }

        return prflDao.insertProfileData(prEnt);
    }

    public static Integer AdminAccess(Long mobile){
        List<RoleEntity> roleEnt=prflDao.GetRoleIdByMobile(mobile);
        int roleId=roleEnt.getFirst().getRoleId();      
      return roleId;
    }

    public static List<String> BindUserRole(){
        var data=prflDao.BindRole();
        List<String> role =new ArrayList<>();
        for (var map : data) {
            role.add(map.get("options").toString());
            
        }
        return role;
    }

    public static List<String> BindStatus(){
        var val=prflDao.BindSts();
        List<String> status =new ArrayList<>();
        for (var map : val) {
            status.add(map.get("options").toString());
            
        }
        return status;

    }
}
