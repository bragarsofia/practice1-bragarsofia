package ua.opnu.practice1_template.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Student;
import ua.opnu.practice1_template.repository.StudentRepository;

import java.util.List;


@Service
public class StudentService {

  private StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository){

    this.studentRepository = studentRepository;
  }

  public Student addNewStudent(Student student){

    return studentRepository.save(student);
  }

  public List<Student> findAllStudents() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      throw new EntityNotFoundException("No students found");
    }
    return students;
  }

  public Student getStudentById(Long id) {
    return studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));
  }

  public Student updateStudent(Long id, Student student){
    Student existing = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));

    existing.setFirstName(student.getFirstName());
    existing.setLastName(student.getLastName());
    existing.setEmail(student.getEmail());
    existing.setEnrollmentDate(student.getEnrollmentDate());
    return studentRepository.save(existing);
  }

  public void deleteStudent(Long id){
    Student existing = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));

    studentRepository.delete(existing);
  }

}
