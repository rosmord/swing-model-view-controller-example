package tableditor.control;


import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.ui.JStudentBase;
import tableditor.ui.specs.IStudentBase;
import tableditor.viewmodel.StudentCreatorViewModel;
import tableditor.viewmodel.StudentNavigatorViewModel;
import tableditor.viewmodel.StudentSharedViewModel;
import tableditor.viewmodel.StudentTableViewModel;

/**
 * This controller builds the others and knows too much.
 * We need to refactor and use dependency injection to avoid this.
 */
public class StudentBaseController {

    private IStudentBase ui = new JStudentBase();
    private StudentTableController tableController;
    private StudentCreatorController creatorController;
    private StudentNavigatorController navigatorController;

    public StudentBaseController(StudentPersistenceFacade baseFacade) {   
        StudentSharedViewModel sharedViewModel = new StudentSharedViewModel(baseFacade);        

        StudentTableViewModel tableViewModel = new StudentTableViewModel(baseFacade, sharedViewModel);     
        this.tableController = new StudentTableController(tableViewModel, ui.getStudentTable());

        StudentCreatorViewModel creatorViewModel = new StudentCreatorViewModel(baseFacade, sharedViewModel);
        this.creatorController = new StudentCreatorController(creatorViewModel, ui.getStudentCreatorPanel());

        StudentNavigatorViewModel navigatorViewModel = new StudentNavigatorViewModel(baseFacade, sharedViewModel);
        this.navigatorController = new StudentNavigatorController(navigatorViewModel, ui.getStudentNavigatorPanel());
    }   

    public void display() {
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JStudentBase.EXIT_ON_CLOSE);
    }
    
}
