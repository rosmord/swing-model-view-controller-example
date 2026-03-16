package tableditor.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tableditor.persistence.entities.Student;
import tableditor.persistence.facade.dto.StudentDTO;

public interface StudentRepository extends JpaRepository<Student, Integer> {
 
    List<StudentDTO> findDTOBy();
}
