package tableditor.control;


import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;

import tableditor.control.adapters.StudentTableAdapter;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.ui.specs.IStudentTable;
import tableditor.viewmodel.StudentTableViewModel;

public class StudentTableController {
    private StudentTableViewModel viewModel;
    private IStudentTable studentTable;

    /**
     * Move to the model !
     */

    public StudentTableController(StudentTableViewModel viewModel, IStudentTable studentTable) {
        this.viewModel = viewModel;
        this.studentTable = studentTable;
        activate();
    }

    private void activate() {       
        // Bind the table
        studentTable.getTable().setModel(new StudentTableAdapter());
        SelectionInList<StudentDTO> selectionModel = viewModel.getStudentSelectionInList();
        Bindings.bind(studentTable.getTable(), selectionModel);
        // Bind the delete button
        studentTable.getDeleteButton().addActionListener(e -> deleteSelected());
        // The delete button should only be enabled when a student is selected.        
        Bindings.bind(studentTable.getDeleteButton(), "enabled",  viewModel.hasSelection());
        
    }

    private void deleteSelected() {
        // Delete selected student.
        // Note : instead of reloading the whole list, we will update it (done in the model)
        viewModel.deleteSelectedStudent();   
    }

}
