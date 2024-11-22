package dev.patientportal.medicalhistorymonitor.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.patientportal.medicalhistorymonitor.Mapper.profile_mapper;
import dev.patientportal.medicalhistorymonitor.Model.profilePage_Model;

@Repository
public class profile_repository {
        @Autowired
private JdbcTemplate jdbcTemplate;

    public Map<String,List<profilePage_Model>> getUserProfileDropdown(profilePage_Model drop){
        String[] drpName = {"Blood Group","Marital Status","Gender"};
        Map<String,List<profilePage_Model>> multiMap = new HashMap<>();
        for(String drp : drpName){
        String query="select options from dropdown where dropdownName = ?";
        List<profilePage_Model> val = jdbcTemplate.query(query, new profile_mapper(), drp);        
        multiMap.put(drp,val);
    }
        return multiMap;    
    }

   
}
