package dev.patientportal.medicalhistorymonitor.Dao;

import java.util.List;
import java.util.Optional;

public interface UserDao<T> {

    String CreateUser(T t);
    List<T> GetUser(long phone);
    void UpdateUser(long phone);
    void DeleteUser(long phone);
}
