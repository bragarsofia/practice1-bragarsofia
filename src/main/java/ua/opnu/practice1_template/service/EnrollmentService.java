package ua.opnu.practice1_template.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.model.Enrollment;
import ua.opnu.practice1_template.model.Student;
import ua.opnu.practice1_template.repository.CourseRepository;
import ua.opnu.practice1_template.repository.EnrollmentRepository;
import ua.opnu.practice1_template.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {


  private EnrollmentRepository enrollmentRepository;
  private StudentRepository studentRepository;
  private CourseRepository courseRepository;

  @Autowired
  public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {

    this.enrollmentRepository = enrollmentRepository;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  public Enrollment addStudentToCourse(Enrollment enrollment) {
    Long studentId = enrollment.getStudent().getId();
    Long courseId = enrollment.getCourse().getId();

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));

    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));

    enrollment.setStudent(student);
    enrollment.setCourse(course);

    return enrollmentRepository.save(enrollment);
  }

  public List<Student> allStudentsInCourse(Long courseId) {
    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));

    List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
    if (enrollments.isEmpty()) {
      throw new EntityNotFoundException("No students enrolled in course with id " + courseId);
    }

    List<Student> students = new ArrayList<>();
    for (Enrollment enrollment : enrollments) {
      students.add(enrollment.getStudent());
    }
    return students;
  }


  public List<Course> allCoursesOfTheStudent(Long studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));

    List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
    if (enrollments.isEmpty()) {
      throw new EntityNotFoundException("No courses found for the student");
    }

    List<Course> courses = new ArrayList<>();
    for (Enrollment enrollment : enrollments) {
      courses.add(enrollment.getCourse());
    }
    return courses;
  }

  public Enrollment updateGradeById(Long id, Enrollment enrollment){
    Enrollment existing = enrollmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found in enrollment with id " + id));
    existing.setGrade(enrollment.getGrade());
    return enrollmentRepository.save(existing);
  }

  public void deleteStudentFromCourse(Long id) {
    Enrollment existing = enrollmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with id " + id));
    enrollmentRepository.delete(existing);
  }

  public List<Enrollment> findAllEnrollments(){

    return enrollmentRepository.findAll();
  }

  }


