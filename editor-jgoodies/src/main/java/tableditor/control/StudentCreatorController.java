package tableditor.control;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.ui.specs.IStudentCreatorPanel;
import tableditor.viewmodel.StudentCreationViewModel;

public class StudentCreatorController {
    private StudentCreationViewModel viewModel;
    private IStudentCreatorPanel ui;

    private ComboBoxAdapter<PromotionDTO> promotionAdapter;

    public StudentCreatorController(StudentCreationViewModel studentCreationViewModel, IStudentCreatorPanel ui) {
        this.viewModel = studentCreationViewModel;
        this.ui = ui;
        activate();
    }

    private void activate() {

        // bind the fields
        Bindings.bind(ui.getFirstnameField(), viewModel.firstName());
        Bindings.bind(ui.getLastnameField(), viewModel.lastName());
        Bindings.bind(ui.getGradeField(), viewModel.grade());

        promotionAdapter = new ComboBoxAdapter<>((ListModel<PromotionDTO>)viewModel.promotions(), viewModel.promotion());
        ui.getPromComboBox().setModel(promotionAdapter);

        ui.getSaveButton().addActionListener(e -> saveStudent());
        Bindings.bind(ui.getSaveButton(), "enabled", viewModel.canSave());
    }

    private void saveStudent() {        
        viewModel.doSaveStudent();
    }
}
