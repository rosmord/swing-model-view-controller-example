package tableditor.control;

import java.awt.TextField;

import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import io.github.parubok.fxprop.SwingPropertySupport;
import io.github.parubok.swingfx.beans.property.IntegerProperty;
import tableditor.fxProperties.TextFieldBinder;
import tableditor.ui.specs.IStudentNavigatorPanel;
import tableditor.viewmodel.StudentNavigatorViewModel;

public class StudentNavigatorController {
    private StudentNavigatorViewModel viewModel;
    private IStudentNavigatorPanel ui;

    public StudentNavigatorController(StudentNavigatorViewModel viewModel, IStudentNavigatorPanel ui) {
        this.viewModel = viewModel;
        this.ui = ui;
        activate();
    }

    private void activate() {
        // Bind the text fields to the view model properties
        ui.getIdField().setEditable(false);
        TextFieldBinder.bindFromInteger(ui.getIdField(),  viewModel.id());
        TextFieldBinder.bindBidirectional(ui.getFirstnameField(), viewModel.firstName());
        TextFieldBinder.bindBidirectional(ui.getLastnameField(), viewModel.lastName());
        TextFieldBinder.bindBidirectional(ui.getGradeField(), viewModel.grade());
        // Bind the grade field
        // Bind the JCombobox list :
        ui.getPromComboBox().setModel(new DefaultEventComboBoxModel<>(viewModel.promotionList()));

        // Bind to the JCombobox value :
        SwingPropertySupport.selectedItemProperty(ui.getPromComboBox()).bindBidirectional(viewModel.selectedPromotion());
        
        
        ui.getNextButton().addActionListener(e -> viewModel.next());
        ui.getPreviousButton().addActionListener(e -> viewModel.previous());

        SwingPropertySupport.enabledProperty(ui.getNextButton()).bind(viewModel.hasNext());
        SwingPropertySupport.enabledProperty(ui.getPreviousButton()).bind(viewModel.hasPrevious());
        SwingPropertySupport.enabledProperty(ui.getUpdateButton()).bind(viewModel.canUpdate());

    }
}
