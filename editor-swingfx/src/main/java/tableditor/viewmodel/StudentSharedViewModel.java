package tableditor.viewmodel;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;

/**
 * Shared model with the observable list of students and promotions.
 */
public class StudentSharedViewModel {
      /**
     * List of all students in glazedlist form.
     */
    private EventList<StudentDTO> students;

    /**
     * List of all possible promotions.
     */

    private EventList<PromotionDTO> promotions;

    public StudentSharedViewModel(StudentPersistenceFacade studentPersistenceFacade) {
        this.students = GlazedLists.eventList(studentPersistenceFacade.findAllStudents());
        this.promotions = GlazedLists.eventList(studentPersistenceFacade.findAllPromotions());
    }

     public EventList<StudentDTO> getStudents() {
        return students;
    }

    public EventList<PromotionDTO> getPromotions() {
        return promotions;
    }
}
