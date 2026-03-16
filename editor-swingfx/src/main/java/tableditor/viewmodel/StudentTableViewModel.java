package tableditor.viewmodel;

import ca.odell.glazedlists.EventList;
import io.github.parubok.swingfx.beans.property.BooleanProperty;
import io.github.parubok.swingfx.beans.property.IntegerProperty;
import io.github.parubok.swingfx.beans.property.SimpleBooleanProperty;
import io.github.parubok.swingfx.beans.property.SimpleIntegerProperty;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.StudentDTO;

/**
 * Logical model for the student list display.
 */
public class StudentTableViewModel {

    private StudentPersistenceFacade studentPersistenceFacade;

    private StudentSharedViewModel sharedViewModel;

    private IntegerProperty selectedRowCount = new SimpleIntegerProperty();

    private BooleanProperty canDelete = new SimpleBooleanProperty();
        

    public StudentTableViewModel(StudentPersistenceFacade studentPersistenceFacade,
            StudentSharedViewModel sharedViewModel) {
        this.studentPersistenceFacade = studentPersistenceFacade;
        this.sharedViewModel = sharedViewModel;
        this.canDelete.bind(selectedRowCount.greaterThanOrEqualTo(1));
    }

    public EventList<StudentDTO> getStudents() {
        return sharedViewModel.getStudents();
    }

    public IntegerProperty selectedRowCount() {
        return selectedRowCount;
    }
    
    public BooleanProperty canDelete() {
        return canDelete;
    }

    public void deleteStudent(int selectedRow) {
        if (selectedRow >= 0) {
            StudentDTO toDelete = sharedViewModel.getStudents().get(selectedRow);
            studentPersistenceFacade.deleteStudent(toDelete.id());
            sharedViewModel.getStudents().remove(selectedRow);
        }
    }



}
