package ua.opnu.practice1_template.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.service.CourseService;
import ua.opnu.practice1_template.repository.InstructorRepository;

@RestController
public class CourseController {

  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping("/course")
  public ResponseEntity<?> addNewCourse(@RequestBody Course course) {
    try {
      Course savedCourse = courseService.addNewCourse(course);
      return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/course")
  public ResponseEntity<?> findAllCourses() {
    try {
      return new ResponseEntity<>(courseService.findAllCourses(), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/course/{id}")
  public ResponseEntity<?> getCourseById(@PathVariable Long id) {
    try {
      Course course = courseService.getCourseById(id);
      return new ResponseEntity<>(course, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/course/instructor/{id}")
  public ResponseEntity<?> findCourseByInstructor(@PathVariable Long id) {
    try {
      return new ResponseEntity<>(courseService.findCourseByInstructor(id), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }


  @PutMapping("/course/{id}")
  public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
    try {
      return new ResponseEntity<>(courseService.updateCourse(id, course), HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/course/{id}")
  public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
    try {
      courseService.deleteCourse(id);
      return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }


}
