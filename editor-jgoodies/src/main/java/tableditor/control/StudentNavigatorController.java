package tableditor.control;

import java.util.List;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.ui.specs.IStudentNavigatorPanel;
import tableditor.viewmodel.StudentNavigatorViewModel;
import tableditor.viewmodel.StudentSharedViewModel;

public class StudentNavigatorController {
    private StudentNavigatorViewModel viewModel;
    private IStudentNavigatorPanel ui;
    private ComboBoxAdapter<PromotionDTO> promotionAdapter;

    public StudentNavigatorController(StudentNavigatorViewModel viewModel, IStudentNavigatorPanel ui) {
        this.viewModel = viewModel;
        this.ui = ui;
        activate();
    }

    private void activate() {

        // bind the fields
        Bindings.bind(ui.getIdField(), viewModel.id());

        Bindings.bind(ui.getFirstnameField(), viewModel.firstName());
        Bindings.bind(ui.getLastnameField(), viewModel.lastName());
        Bindings.bind(ui.getGradeField(), viewModel.grade());

        promotionAdapter = new ComboBoxAdapter<>((List<PromotionDTO>)viewModel.promotions(),
                viewModel.promotion());
        ui.getPromComboBox().setModel(promotionAdapter);

        ui.getNextButton().addActionListener(e -> viewModel.next());
        ui.getPreviousButton().addActionListener(e -> viewModel.previous());
        ui.getUpdateButton().addActionListener(e -> viewModel.update());

        // Binds the button activation
        Bindings.bind(ui.getNextButton(), "enabled", viewModel.canNext());
        Bindings.bind(ui.getPreviousButton(), "enabled", viewModel.canPrevious());
        Bindings.bind(ui.getUpdateButton(), "enabled", viewModel.canUpdate());
    }
}
