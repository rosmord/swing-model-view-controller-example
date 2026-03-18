package tableditor.control;

import tableditor.ui.JStudentBase;
import tableditor.ui.specs.IStudentBase;
import tableditor.viewmodel.StudentCreationViewModel;
import tableditor.viewmodel.StudentNavigatorViewModel;
import tableditor.viewmodel.StudentSharedViewModel;
import tableditor.viewmodel.StudentTableViewModel;


public class StudentBaseController {

    private StudentSharedViewModel persistenceLogic;
    private IStudentBase ui = new JStudentBase();
    private StudentTableController tablePresenter;
    private StudentCreatorController studentCreatorPresenter;
    private StudentNavigatorController studentNavigatorPresenter;

    public StudentBaseController(StudentSharedViewModel studentSharedViewModel) {        
        this.persistenceLogic = studentSharedViewModel;
        // Temporary code. Should be done otherwise (dependency injection ?)
        this.tablePresenter = new StudentTableController(new StudentTableViewModel(studentSharedViewModel), ui.getStudentTable());    
        this.studentCreatorPresenter = new StudentCreatorController(new StudentCreationViewModel(studentSharedViewModel), ui.getStudentCreatorPanel());
        this.studentNavigatorPresenter = new StudentNavigatorController(new StudentNavigatorViewModel(studentSharedViewModel), ui.getStudentNavigatorPanel());
    }   

    public void display() {
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JStudentBase.EXIT_ON_CLOSE);
    }
    
}
