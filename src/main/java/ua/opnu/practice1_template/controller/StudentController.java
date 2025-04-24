package ua.opnu.practice1_template.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Student;
import org.springframework.http.ResponseEntity;
import ua.opnu.practice1_template.service.StudentService;


@RestController
public class StudentController {

  private StudentService studentService;


  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("/student")
  public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
    return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
  }

  @GetMapping("/student")
  public ResponseEntity<?> findAllStudents() {
    try {
      return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<?> getStudentById(@PathVariable Long id) {
    try {
      return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/student/{id}")
  public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
   try {
     return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
   } catch (EntityNotFoundException e){
     return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
   }
  }

  @DeleteMapping("/student/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    try {
      studentService.deleteStudent(id);
      return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }


}