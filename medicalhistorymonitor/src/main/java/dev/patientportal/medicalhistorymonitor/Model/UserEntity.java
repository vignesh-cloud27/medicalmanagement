package dev.patientportal.medicalhistorymonitor.Model;


public class UserEntity {

    public String name, password; 
    public long phone;   

    public String getName(){
        return name;
    } 
    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    } 
    public void setPassword(String password){
        this.password = password;
    }

    public long getPhone(){
        return phone;
    } 
    public void setPhone(long phone){
        this.phone = phone;
    }

}
