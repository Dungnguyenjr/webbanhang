package com.tn.Controller;

import com.tn.Entity.Department;
import com.tn.Repository.Departmentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private Departmentrepo departmentrepo;

    @GetMapping("department")
    public String departmentDemo() {
        return "Departmentlist";
    }

    @GetMapping("listdepartment")
    public String getAll(Model model) {
        List<Department> departments = departmentrepo.findAll();
        model.addAttribute("departments", departments);
        return "Departmentlist";
    }
}
