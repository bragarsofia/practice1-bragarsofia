package ua.opnu.practice1_template.repository;

import org.springframework.data.repository.CrudRepository;
import ua.opnu.practice1_template.model.Instructor;

import java.util.List;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {

List<Instructor> findAll();
}
