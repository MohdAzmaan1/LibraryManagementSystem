package com.example.Library.Management.System.Controllers;

import com.example.Library.Management.System.DTO.StudentUpdateMobRequestDTO;
import com.example.Library.Management.System.Models.Student;
import com.example.Library.Management.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/get-user")
    public String findNameByEmail(@RequestParam ("email") String email) {
        return studentService.findNameByEmail(email);
    }

    @PutMapping("/update-mobNo")
    public String updateMobileNumber(@RequestBody StudentUpdateMobRequestDTO studentUpdateMobRequestDTO){
        return studentService.updateMobileNumber(studentUpdateMobRequestDTO);
    }
}
