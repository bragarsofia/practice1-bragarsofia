package ua.opnu.practice1_template.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.Assignment;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.repository.CourseRepository;
import ua.opnu.practice1_template.service.AssignmentService;

import java.util.List;

@RestController
public class AssignmentController {

  private AssignmentService assignmentService;
  private CourseRepository courseRepository;

  @Autowired
  public AssignmentController(AssignmentService assignmentService, CourseRepository courseRepository) {
    this.assignmentService = assignmentService;
    this.courseRepository = courseRepository;
  }

  @PostMapping("/assignment")
  public ResponseEntity <Assignment> addNewAssignment(@RequestBody Assignment assignment) {
    return new ResponseEntity<> (assignmentService.addNewAssignment(assignment), HttpStatus.CREATED);
  }

  @GetMapping("/assignment/course/{courseId}")
  public ResponseEntity<?> getAllAssignmentsByCourse(@PathVariable Long courseId) {
    try {
      Course course = courseRepository.findById(courseId)
              .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
      List<Assignment> assignments = assignmentService.getAllAssignmentsByCourse(course);
      return new ResponseEntity<>(assignments, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/assignment/{id}")
  public ResponseEntity<? > updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
    try {
      Assignment updated = assignmentService.updateAssignment(id, assignment);
      return new ResponseEntity<>(updated, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/assignment/{id}")
  public ResponseEntity<String> deleteAssignment(@PathVariable Long id) {
    try {
      assignmentService.deleteAssignment(id);
      return new ResponseEntity<>("Assignment deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
