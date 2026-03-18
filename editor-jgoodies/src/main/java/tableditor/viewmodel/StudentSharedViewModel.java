package tableditor.viewmodel;

import com.jgoodies.common.collect.ArrayListModel;

import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;

/**
 * A global view model shared by all our views.
 * 
 * This might not be such a good idea, but this is also somehow a tech demo.
 * 
 * We might also consider having :
 * - a bunch of local view models for the content of each view ;
 * - a global view model for shared data (the lists, mainly).
 */
public class StudentSharedViewModel {

    /**
     * List of all students.
     */
    private ArrayListModel<StudentDTO> students;

    /**
     * List of all possible promotions.
     */

    private ArrayListModel<PromotionDTO> promotions;

    /**
     * Access to the persistence layer.
     */
    private StudentPersistenceFacade studentPersistenceFacade;

    public StudentSharedViewModel(StudentPersistenceFacade studentPersistenceFacade) {
        this.studentPersistenceFacade = studentPersistenceFacade;
        this.students = new ArrayListModel<>(studentPersistenceFacade.findAllStudents());
        this.promotions = new ArrayListModel<>(studentPersistenceFacade.findAllPromotions());
    }

    public ArrayListModel<StudentDTO> getStudents() {
        return students;
    }

    public ArrayListModel<PromotionDTO> getPromotions() {
        return promotions;
    }

    

    public StudentPersistenceFacade getStudentPersistenceFacade() {
        return studentPersistenceFacade;
    }

}
