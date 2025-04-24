package ua.opnu.practice1_template.repository;

import org.springframework.data.repository.CrudRepository;
import ua.opnu.practice1_template.model.Enrollment;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

  List<Enrollment> findByCourseId(Long courseId);
  List<Enrollment> findByStudentId(Long studentId);
  List<Enrollment> findAll();
}
