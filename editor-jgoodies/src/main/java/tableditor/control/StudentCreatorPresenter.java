package tableditor.control;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.ui.specs.IStudentCreatorPanel;
import tableditor.viewmodel.StudentViewModel;
import tableditor.viewmodel.StudentViewModel.StudentCreationViewModel;

public class StudentCreatorPresenter {
    private StudentViewModel logic;
    private IStudentCreatorPanel ui;

    private ComboBoxAdapter<PromotionDTO> promotionAdapter;

    public StudentCreatorPresenter(StudentViewModel logic, IStudentCreatorPanel ui) {
        this.logic = logic;
        this.ui = ui;
        activate();
    }

    private void activate() {
        StudentCreationViewModel localModel = logic.getStudentCreationViewModel();

        // bind the fields
        Bindings.bind(ui.getFirstnameField(), localModel.firstName());
        Bindings.bind(ui.getLastnameField(), localModel.lastName());
        Bindings.bind(ui.getGradeField(), localModel.grade());

        promotionAdapter = new ComboBoxAdapter<>((ListModel<PromotionDTO>)logic.getPromotions(), localModel.promotion());
        ui.getPromComboBox().setModel(promotionAdapter);

        ui.getSaveButton().addActionListener(e -> saveStudent());
        Bindings.bind(ui.getSaveButton(), "enabled", localModel.canSave());
    }

    private void saveStudent() {
        logic.getStudentCreationViewModel().doSaveStudent();
    }
}
