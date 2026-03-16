package tableditor.control;

import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import io.github.parubok.fxprop.SwingPropertySupport;
import tableditor.fxProperties.TextFieldBinder;
import tableditor.ui.specs.IStudentCreatorPanel;
import tableditor.viewmodel.StudentCreatorViewModel;

public class StudentCreatorController {
    private StudentCreatorViewModel viewModel;
    private IStudentCreatorPanel ui;

    public StudentCreatorController(StudentCreatorViewModel viewModel, IStudentCreatorPanel ui) {
        this.viewModel = viewModel;        
        this.ui = ui;
        activate();
    }

    private void activate() {
        TextFieldBinder.bindBidirectional(ui.getFirstnameField(), viewModel.firstName());
        TextFieldBinder.bindBidirectional(ui.getLastnameField(), viewModel.lastName());
        TextFieldBinder.bindBidirectional(ui.getGradeField(), viewModel.grade());
        // Bind the grade field
        // Bind the JCombobox list :
        ui.getPromComboBox().setModel(new DefaultEventComboBoxModel<>(viewModel.promotionList()));

        // Bind to the JCombobox value :
        SwingPropertySupport.selectedItemProperty(ui.getPromComboBox()).bindBidirectional(viewModel.selectedPromotion());
        
        ui.getSaveButton().addActionListener(e -> saveStudent());
        SwingPropertySupport.enabledProperty(ui.getSaveButton()).bind(viewModel.canSave());
    }

    private void saveStudent() {
        viewModel.saveStudent();
    }
}
