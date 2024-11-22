package dev.patientportal.medicalhistorymonitor.Controlller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.patientportal.medicalhistorymonitor.Dao.profile_repository;
import dev.patientportal.medicalhistorymonitor.Model.profilePage_Model;

@Controller
public class Register_Controller {
  @Autowired
  private profile_repository profilerepo;

  @GetMapping("/layout")
  public String layout() {
    return "sidebar_layout";
  }

  @RequestMapping(value = "/profile", method = { RequestMethod.GET, RequestMethod.POST })
  public String getprofilepage(Model model, profilePage_Model drop) {
    ArrayList<String> bloodGrp = new ArrayList<String>();
    ArrayList<String> gender = new ArrayList<String>();
    ArrayList<String> mrtlSts = new ArrayList<String>();
    Map<String, List<profilePage_Model>> dropdown = profilerepo.getUserProfileDropdown(drop);

    for (var dropOption : dropdown.entrySet()) {
      switch (dropOption.getKey()) {
        case "Blood Group":
          for (var option : dropOption.getValue()) {
            bloodGrp.add(option.getOption());
          }
          model.addAttribute("BloodGrp", bloodGrp);
          break;
        case "Marital Status":
          for (var option : dropOption.getValue()) {
            mrtlSts.add(option.getOption());
          }
          model.addAttribute("MaritalSts", mrtlSts);
          // mrtlSts.add(dropOption.getValue().toString());
          break;
        case "Gender":
          for (var option : dropOption.getValue()) {
            gender.add(option.getOption());
          }
          model.addAttribute("gender", gender);
          break;

        default:
          break;
      }

    }
    return "profilepage";
  }
}
