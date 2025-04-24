package ua.opnu.practice1_template.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Enrollment;
import ua.opnu.practice1_template.service.EnrollmentService;

import java.util.List;

@RestController
public class EnrollmentController {

  private EnrollmentService enrollmentService;

  @Autowired
  public EnrollmentController(EnrollmentService enrollmentService) {
    this.enrollmentService = enrollmentService;
  }

  @PostMapping("/enrollment")
  public ResponseEntity<Enrollment> addStudentToCourse(@RequestBody Enrollment enrollment) {
    return new ResponseEntity<>(enrollmentService.addStudentToCourse(enrollment), HttpStatus.CREATED);
  }

  @GetMapping("/course/{courseId}/students")
  public ResponseEntity<?> allStudentsInCourse(@PathVariable Long courseId) {
    try {
      return new ResponseEntity<>(enrollmentService.allStudentsInCourse(courseId), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

    @GetMapping("/student/{studentId}/courses")
  public ResponseEntity<?> allCoursesOfTheStudent(@PathVariable Long studentId) {
  try {
    return new ResponseEntity<>(enrollmentService.allCoursesOfTheStudent(studentId), HttpStatus.OK);
  } catch (EntityNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}

  @PutMapping("/enrollment/{id}")
  public ResponseEntity<?> updateGradeById(@PathVariable Long id,@RequestBody Enrollment enrollment) {
    try {
      return new ResponseEntity<>(enrollmentService.updateGradeById(id, enrollment), HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/enrollment/{id}")
  public ResponseEntity<String> deleteStudentFromCourse(@PathVariable Long id) {
    try {
      enrollmentService.deleteStudentFromCourse(id);
      return new ResponseEntity<>("Enrollment deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/enrollment")
  public ResponseEntity<List<Enrollment>> findAllEnrollments() {
    return new ResponseEntity<>(enrollmentService.findAllEnrollments(), HttpStatus.OK);
  }


}
