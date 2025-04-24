package ua.opnu.practice1_template.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Instructor;
import ua.opnu.practice1_template.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {

  private InstructorRepository instructorRepository;

  @Autowired
  public InstructorService(InstructorRepository instructorRepository){

    this.instructorRepository = instructorRepository;
  }

  public Instructor addNewInstructor(Instructor instructor){

    return instructorRepository.save(instructor);
  }

  public List<Instructor> findAllInstructors() {
    List<Instructor> instructors = instructorRepository.findAll();
    if (instructors.isEmpty()) {
      throw new EntityNotFoundException("No instructors found");
    }
    return instructors;
  }

  public Instructor getInstructorById(Long id) {
    return instructorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id " + id));
  }

  public Instructor updateInstructor(Long id, Instructor instructor){
    Instructor existing = instructorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id " + id));

    existing.setFirstName(instructor.getFirstName());
    existing.setLastName(instructor.getLastName());
    existing.setEmail(instructor.getEmail());
    existing.setDepartment(instructor.getDepartment());
    return instructorRepository.save(existing);
  }

  public void deleteInstructor(Long id){
    Instructor existing = instructorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id " + id));

    instructorRepository.delete(existing);
  }

}
