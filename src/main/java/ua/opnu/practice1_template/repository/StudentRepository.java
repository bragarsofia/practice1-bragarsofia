package ua.opnu.practice1_template.repository;

import org.springframework.data.repository.CrudRepository;
import ua.opnu.practice1_template.model.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
  List<Student> findAll();

}
