package ua.opnu.practice1_template.repository;

import org.springframework.data.repository.CrudRepository;
import ua.opnu.practice1_template.model.Course;
import ua.opnu.practice1_template.model.Instructor;


import java.util.List;


public interface CourseRepository extends CrudRepository<Course, Long> {

  List<Course> findAll();
  List<Course> findCourseByInstructor(Instructor instructor);

}
