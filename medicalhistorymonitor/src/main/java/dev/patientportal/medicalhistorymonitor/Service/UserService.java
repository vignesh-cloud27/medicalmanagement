package dev.patientportal.medicalhistorymonitor.Service;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import dev.patientportal.medicalhistorymonitor.Dao.UserDao;
import dev.patientportal.medicalhistorymonitor.Model.UserEntity;
// import lombok.AllArgsConstructor;

@Service
// @AllArgsConstructor
// implements UserDetailsService
public class UserService  {

    private static UserDao<UserEntity> userDao;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    // {
    //     List<UserEntity> usrEnt = userDao.GetUser(Long.parseLong(username));
    //     if (usrEnt.size() != 0) {
    //         return User.builder()
    //         .username(Long.toString(usrEnt.getFirst().getPhone()))
    //         .password(usrEnt.getFirst().getPassword())
    //         .build();
    //     }
    //     else
    //     {
    //         throw new UsernameNotFoundException(username);
    //     }
    // }

    public UserService(UserDao<UserEntity> userDao) {
        this.userDao = userDao;
    }

    public static String CreateUser(UserEntity usrEnt)
    {
        return userDao.CreateUser(usrEnt);
    }

    public static List<UserEntity> GetUser(long phone)
    {
        return userDao.GetUser(phone);
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    // }
    

}
