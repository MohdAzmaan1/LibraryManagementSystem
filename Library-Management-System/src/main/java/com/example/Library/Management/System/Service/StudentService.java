package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.DTO.StudentUpdateMobRequestDTO;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Models.Card;
import com.example.Library.Management.System.Models.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student){

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);   //Card status is being set.
        card.setStudent(student);                  //Foreign key attribute

        student.setCard(card);
        //If there was a unidirectional mapping : we had to save both of them.
        //studentRepo.save () and cardRepo.save()
        //But here we are using bidirectional mapping sp Child will automatically be saved.

        studentRepository.save(student);
        return "Student and Card Details Are Added Successfully";
    }

    public String findNameByEmail(String email){
        Student student = studentRepository.findNameByEmail(email);
        return student.getName();
    }

    public String updateMobileNumber(StudentUpdateMobRequestDTO studentUpdateMobRequestDTO){
        //Convert the DTO to entity
        //first we will try to fetch the original data
        Student originalStudent = studentRepository.findById(studentUpdateMobRequestDTO.getId()).get();

        //We will keep the other properties as it is and change only the required parameter
        originalStudent.setMobileNumber(studentUpdateMobRequestDTO.getMobileNumber());
        studentRepository.save(originalStudent);
        return "Student Updated Successfully";
    }
}
