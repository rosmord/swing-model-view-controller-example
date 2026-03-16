package tableditor.control;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.ui.specs.IStudentNavigatorPanel;
import tableditor.viewmodel.StudentViewModel;
import tableditor.viewmodel.StudentViewModel.StudentNavigatorViewModel;

public class StudentNavigatorPresenter {
    private StudentViewModel persistenceLogic;
    private IStudentNavigatorPanel ui;
    private ComboBoxAdapter<PromotionDTO> promotionAdapter;

    public StudentNavigatorPresenter(StudentViewModel persistenceLogic, IStudentNavigatorPanel ui) {
        this.persistenceLogic = persistenceLogic;
        this.ui = ui;
        activate();
    }

    private void activate() {
        StudentNavigatorViewModel navModel = persistenceLogic.getStudentNavigatorViewModel();

        // bind the fields
        Bindings.bind(ui.getIdField(), navModel.id());

        Bindings.bind(ui.getFirstnameField(), navModel.firstName());
        Bindings.bind(ui.getLastnameField(), navModel.lastName());
        Bindings.bind(ui.getGradeField(), navModel.grade());

        promotionAdapter = new ComboBoxAdapter<>((ListModel<PromotionDTO>) persistenceLogic.getPromotions(),
                navModel.promotion());
        ui.getPromComboBox().setModel(promotionAdapter);

        ui.getNextButton().addActionListener(e -> navModel.next());
        ui.getPreviousButton().addActionListener(e -> navModel.previous());
        ui.getUpdateButton().addActionListener(e -> navModel.update());

        // Binds the button activation
        Bindings.bind(ui.getNextButton(), "enabled", navModel.canNext());
        Bindings.bind(ui.getPreviousButton(), "enabled", navModel.canPrevious());
        Bindings.bind(ui.getUpdateButton(), "enabled", navModel.canUpdate());

    }
}
