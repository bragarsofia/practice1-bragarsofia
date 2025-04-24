package ua.opnu.practice1_template.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;
  private String description;
  private Integer credits;

  @ManyToOne
  @JoinColumn(name = "instructor_id", nullable = false)
  private Instructor instructor;

}
