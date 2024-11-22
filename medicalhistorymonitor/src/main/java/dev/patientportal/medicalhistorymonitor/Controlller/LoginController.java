package dev.patientportal.medicalhistorymonitor.Controlller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.patientportal.medicalhistorymonitor.Model.UserEntity;
import dev.patientportal.medicalhistorymonitor.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController{

    @GetMapping("/login")
    public String login() {
        return "login";
    } 
    
    // Controller Method to SignUp user
    @RequestMapping(value = "/SignUpUser", method = RequestMethod.POST)
    public @ResponseBody String SignUpUser(@RequestBody UserEntity userEntity) throws ParseException, IOException {
        List<UserEntity> usr = UserService.GetUser(userEntity.phone);
        if (usr.size() != 0) {
            return new String("User already exist");
        }
        String encPwd = EncryptPW(userEntity.getPassword());
        userEntity.setPassword(encPwd);
        String response = UserService.CreateUser(userEntity);
        return new String(response);
    }

    // Controller Method to Login user
    @RequestMapping(value = "/LoginUser", method = RequestMethod.POST)
    public @ResponseBody String LoginUser(@RequestBody UserEntity userEntity,HttpSession session,HttpServletRequest request) throws IllegalStateException {
       // UserService.loadUserByUsername(Long.toString(userEntity.phone));
       session = request.getSession();
       if(session.isNew()){

       }
       else{
        session.invalidate();
    }
    

        List<UserEntity> usr = UserService.GetUser(userEntity.phone);
        if (usr.size() == 0) {
            return new String("User not available please sign in");
        }
        String depwd = DecryptPW(usr.getFirst().getPassword().getBytes());
        String pwd = userEntity.getPassword();

        if (depwd.equals(pwd)) { 
            session.setAttribute("phone", Long.toString(userEntity.phone) );
            session.setAttribute("password", pwd);                                  
            return "Home";
        }
        return new String("Phone or password is incorrect.");
    }

    @RequestMapping("/home")
    public String viewHomePage(HttpServletRequest request,HttpSession session) {
        String phone = (String)session.getAttribute("phone");
        String password = (String)session.getAttribute("password");
        if(phone != null && password != null)
        {
            return "dashboard";
        }
        return login();

    }

    // Controller Method to Encrypt given password
    public String EncryptPW(String pwd) {
        try {

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, GenerateKey(), new IvParameterSpec((new byte[16])));

            byte[] cipherText = c.doFinal(pwd.getBytes(Charset.forName("UTF-8")));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            return (e.toString());
        }
    }

    // Controller Method to Decrypt given password
    public String DecryptPW(byte[] encValue) {
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, GenerateKey(), new IvParameterSpec((new byte[16])));
            byte[] enctVal = c.doFinal(Base64.getDecoder().decode(encValue));
            return new String(enctVal);
        } catch (Exception e) {
            return e.toString();
        }
    }

    // Controller Method to generate key for password encryption and decryption
    public SecretKeySpec GenerateKey() {
        String salt = "ThisIsASecretKey";
        byte[] raw = salt.toString().getBytes(Charset.forName("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        return keySpec;
    }

}
