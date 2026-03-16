package tableditor.persistence.facade;

import java.util.List;

import org.springframework.stereotype.Service;

import tableditor.persistence.entities.Student;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.persistence.repositories.PromotionRepository;
import tableditor.persistence.repositories.StudentRepository;

/**
 * Facade for Student-related operations.
 */
@Service
public class StudentPersistenceFacade {

    StudentRepository studentRepository;

    PromotionRepository promotionRepository;




    public StudentPersistenceFacade(StudentRepository studentRepository, PromotionRepository promotionRepository) {
        this.studentRepository = studentRepository;
        this.promotionRepository = promotionRepository;
    }
    
    /**
     * Saves a new student.
     * @param studentDTO student data (the id won't be used).
     * 
     * @return the id of the saved student.
     */
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student toSave = new Student(studentDTO.firstname(), studentDTO.lastname(), studentDTO.prom(), studentDTO.grade());
        Student savedStudent = studentRepository.save(toSave);        
        return new StudentDTO(savedStudent.getId(), savedStudent.getFirstname(), savedStudent.getLastname(), savedStudent.getProm(), savedStudent.getGrade());
    }

    /**
     * Updates an existing student.
     * @param studentDTO
     */
    public void updateStudent(StudentDTO studentDTO) {

    }

    /**
     * Find all students.
     */

    public List<StudentDTO> findAllStudents() {
        return studentRepository.findDTOBy();
    }

    public StudentDTO findStudentById(int id) {
        return null;
    }

    public List<PromotionDTO> findAllPromotions() {
        return promotionRepository.findDTOBy();
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
