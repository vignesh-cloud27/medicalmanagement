package dev.patientportal.medicalhistorymonitor.Model;

public class RoleEntity {

    //private Integer id;
    private Integer roleId;
    private String roleName;
    private Long phone;

    public RoleEntity(Integer id,Integer roleId,String roleName,Long phone){
        //this.id=id;
        this.roleId=roleId;
        this.roleName=roleName;
        this.phone=phone;
    }
    public RoleEntity(){}

    // public Integer getId(){
    //     return this.id;
    // }

    // public void setId(Integer id){
    //     this.id=id;
    // }

    public Integer getRoleId(){
        return this.roleId;
    }
    public void setRoleId(Integer roleId){
        this.roleId=roleId;
    }
    public String roleName(){
        return this.roleName;
    }
    public void setRoleName(String roleName){
        this.roleName=roleName;
    }
    public Long getPhone(){
        return this.phone;
    }
    public void setPhone(Long phone){
        this.phone=phone;
    }
}
