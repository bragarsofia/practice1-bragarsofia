package ua.opnu.practice1_template.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Assignment;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.repository.AssignmentRepository;
import ua.opnu.practice1_template.repository.CourseRepository;

import java.util.List;

@Service
public class AssignmentService {

  private AssignmentRepository assignmentRepository;
  private CourseRepository courseRepository;

  @Autowired
  public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository) {
    this.assignmentRepository = assignmentRepository;
    this.courseRepository = courseRepository;
  }

  public Assignment addNewAssignment(Assignment assignment) {
    Long courseId = assignment.getCourse().getId();
    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
    assignment.setCourse(course);
    return assignmentRepository.save(assignment);
  }

  public List<Assignment> getAllAssignmentsByCourse(Course course) {
    return assignmentRepository.getAllAssignmentsByCourse(course);
  }

  public Assignment updateAssignment(Long id, Assignment assignment){
    Assignment existing = assignmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id " + id));

    Long courseId = assignment.getCourse().getId();
    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));

    existing.setCourse(course);
    existing.setTitle(assignment.getTitle());
    existing.setDescription(assignment.getDescription());
    existing.setDueDate(assignment.getDueDate());

    return assignmentRepository.save(existing);
  }

  public void deleteAssignment(Long id){
    Assignment existing = assignmentRepository.findById(id)
   .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id " + id));
    assignmentRepository.delete(existing);
  }




}
