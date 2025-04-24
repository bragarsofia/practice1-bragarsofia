package ua.opnu.practice1_template.repository;

import org.springframework.data.repository.CrudRepository;
import ua.opnu.practice1_template.model.Assignment;
import ua.opnu.practice1_template.model.Course;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

  List<Assignment> getAllAssignmentsByCourse(Course course);

}
