package ua.opnu.practice1_template.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.model.Instructor;
import ua.opnu.practice1_template.model.Student;
import ua.opnu.practice1_template.repository.CourseRepository;
import ua.opnu.practice1_template.repository.InstructorRepository;

import java.util.List;

@Service
public class CourseService {

  private InstructorRepository instructorRepository;
  private CourseRepository courseRepository;

  @Autowired
  public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
    this.courseRepository = courseRepository;
    this.instructorRepository = instructorRepository;
  }

  public Course addNewCourse(Course course){
    Long instructorId = course.getInstructor().getId();
    Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id " + instructorId));

    course.setInstructor(instructor);
    return courseRepository.save(course);
  }

  public List<Course> findAllCourses() {
    List<Course> courses = courseRepository.findAll();
    if (courses.isEmpty()) {
      throw new EntityNotFoundException("No courses found");
    }
    return courses;
  }

  public Course getCourseById(Long id) {
    return courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
  }

  public List<Course> findCourseByInstructor(Long instructorId) {
    Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id: " + instructorId));

    List<Course> courses = courseRepository.findCourseByInstructor(instructor);
    if (courses.isEmpty()) {
      throw new EntityNotFoundException("No courses found for instructor with id: " + instructorId);
    }

    return courses;
  }

  public Course updateCourse(Long id, Course course) {
    Course existingCourse = courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

    Instructor instructor = instructorRepository.findById(course.getInstructor().getId())
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id: " + course.getInstructor().getId()));

    existingCourse.setInstructor(instructor);
    existingCourse.setTitle(course.getTitle());
    existingCourse.setDescription(course.getDescription());
    existingCourse.setCredits(course.getCredits());

    return courseRepository.save(existingCourse);
  }

  public void deleteCourse(Long id){
    Course existing = courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + id));

    courseRepository.delete(existing);
  }
}
