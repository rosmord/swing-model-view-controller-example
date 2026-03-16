package tableditor.control;

import javax.swing.JTable;

import ca.odell.glazedlists.swing.DefaultEventTableModel;
import io.github.parubok.fxprop.SwingPropertySupport;
import io.github.parubok.swingfx.beans.binding.Binding;
import tableditor.control.adapters.StudentTableFormat;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.ui.specs.IStudentTable;
import tableditor.viewmodel.StudentTableViewModel;

public class StudentTableController {
    private StudentTableViewModel viewModel;
    private IStudentTable studentTable;

    public StudentTableController(StudentTableViewModel viewModel, IStudentTable studentTable) {
        this.viewModel = viewModel;
        this.studentTable = studentTable;
        activate();
    }

    private void activate() {
        JTable table = studentTable.getTable();
        table.setModel(new DefaultEventTableModel<StudentDTO>(viewModel.getStudents(), new StudentTableFormat()));

        // Bind selected row count of the table to the selected row count of the view model        
        viewModel.selectedRowCount().bind(SwingPropertySupport.selectedRowCountProperty(table));

        SwingPropertySupport.enabledProperty(studentTable.getDeleteButton())
            .bind(viewModel.canDelete());
        
        // Bind the button
        studentTable.getDeleteButton().addActionListener(e -> deleteSelectedStudents());
    }

    private void deleteSelectedStudents() {
        // Look if we can bind selected row to the view model (it's done in the jgoodies version)
        // It can be done.
        viewModel.deleteStudent(studentTable.getTable().getSelectedRow());
    }
}
