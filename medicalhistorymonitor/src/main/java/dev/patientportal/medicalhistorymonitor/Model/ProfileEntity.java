package dev.patientportal.medicalhistorymonitor.Model;

import java.util.List;

public class ProfileEntity {

    // Profile entity Variables
    public String name;
    public Long mobile;
    public float height;
    public float weight;
    public String blood_group;
    public String dob;
    public String address;
    public String marital_status;
    public String gender;
    public String age;
    public String email;
    public byte[] photo;
    public String file_name;
    public String filePath;
    public List<String> role;
    public  List<String> status;

    // Profile entity constructor
    public ProfileEntity(String name, Long mobile, float height, float weight, String blood_group, String dob,
            String address,
            String marital_status, String gender, String age, String email, byte[] photo,String file_name,List<String> role, List<String> status) {
        this.name = name;
        this.mobile = mobile;
        this.height = height;
        this.weight = weight;
        this.blood_group = blood_group;
        this.dob = dob;
        this.address = address;
        this.marital_status = marital_status;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.photo = photo;
        this.file_name=file_name;
        this.role=role;
        this.status=status;
    }

    public ProfileEntity()
    {}

    // Profile entity Getters and Setters.
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return this.mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBloodGrp() {
        return this.blood_group;
    }

    public void setBloodGrp(String bloodGrp) {
        this.blood_group = bloodGrp;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritialsts() {
        return this.marital_status;
    }

    public void setMaritialsts(String maritialSts) {
        this.marital_status = maritialSts;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public String getFileName() {
        return this.file_name;
    }

    public void setFileName(String filename) {
        this.file_name = filename;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getRole(){
        return this.role;
    }

    public void setRole(List<String> role){
        this.role=role;
    }
    public List<String> getStatus(){
        return this.status;
    }

    public void setStatus(List<String> sts){
        this.status=sts;
    }
}

