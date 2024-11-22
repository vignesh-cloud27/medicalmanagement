package dev.patientportal.medicalhistorymonitor.Controlller;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.patientportal.medicalhistorymonitor.Model.ProfileEntity;
import dev.patientportal.medicalhistorymonitor.Service.ProfileService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {
   

    @RequestMapping(value = "/profile")
    public String getprofilepage(HttpSession session) {
        LoginController login = new LoginController();
        String phone = (String) session.getAttribute("phone");
        String password = (String) session.getAttribute("password");
        int roleId=ProfileService.AdminAccess(Long.parseLong(phone));
        if (phone != null && password != null && (roleId==1||roleId==2)) {
            return "profilepage";
        }
        return login.login();

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Path UploadImage(@RequestParam("httpPostedFileBase") MultipartFile file,
            HttpSession session)
            throws FileUploadException, IOException {
        long mobile = Long.parseLong((String) session.getAttribute("phone"));
        Path path=ProfileService.UploadProfileImage(file, mobile);
        return path;
    }

    @RequestMapping(value = "profileData", method = RequestMethod.POST)
    public @ResponseBody Object BindUSerData(HttpSession session,Model model) throws FileUploadException, IOException  {
        long mobile = Long.parseLong((String) session.getAttribute("phone"));     
        return  ProfileService.BindData(mobile,session,model);
    } 

    @PostMapping("/profilePage")
    public @ResponseBody ProfileEntity createUser(@RequestBody ProfileEntity
    usrprent)
    throws IOException {
    return ProfileService.AddUser(usrprent);
    }
}
