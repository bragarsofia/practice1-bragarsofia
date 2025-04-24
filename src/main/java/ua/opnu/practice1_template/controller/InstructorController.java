package ua.opnu.practice1_template.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Instructor;
import ua.opnu.practice1_template.service.InstructorService;

@RestController
public class InstructorController {

  private InstructorService instructorService;

  @Autowired
  public InstructorController(InstructorService instructorService) {
    this.instructorService = instructorService;
  }

  @PostMapping("/instructor")
  public ResponseEntity<Instructor> addNewInstructor(@RequestBody Instructor instructor) {
    return new ResponseEntity<>(instructorService.addNewInstructor(instructor), HttpStatus.CREATED);
  }

  @GetMapping("/instructor")
  public ResponseEntity<?> findAllInstructors() {
    try {
      return new ResponseEntity<>(instructorService.findAllInstructors(), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/instructor/{id}")
  public ResponseEntity<?> getInstructorById(@PathVariable Long id) {
    try {
      return new ResponseEntity<>(instructorService.getInstructorById(id), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/instructor/{id}")
  public ResponseEntity<?> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
    try {
      return new ResponseEntity<>(instructorService.updateInstructor(id, instructor), HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/instructor/{id}")
  public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {
    try {
      instructorService.deleteInstructor(id);
      return new ResponseEntity<>("Instructor deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

}
