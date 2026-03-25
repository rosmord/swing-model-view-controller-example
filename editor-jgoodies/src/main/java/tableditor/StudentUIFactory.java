package tableditor;

import tableditor.control.StudentCreatorController;
import tableditor.control.StudentNavigatorController;
import tableditor.control.StudentTableController;
import tableditor.ui.JStudentBase;
import tableditor.ui.specs.IStudentBase;
import tableditor.viewmodel.StudentCreationViewModel;
import tableditor.viewmodel.StudentNavigatorViewModel;
import tableditor.viewmodel.StudentSharedViewModel;
import tableditor.viewmodel.StudentTableViewModel;

/**
 * Builder for the student UI.
 */
public class StudentUIFactory {

    private StudentSharedViewModel sharedViewModel;
    private IStudentBase ui = new JStudentBase();
    private StudentTableController tablePresenter;
    private StudentCreatorController studentCreatorPresenter;
    private StudentNavigatorController studentNavigatorPresenter;

    public StudentUIFactory(StudentSharedViewModel studentSharedViewModel) {        
        this.sharedViewModel = studentSharedViewModel;
        this.tablePresenter = new StudentTableController(new StudentTableViewModel(studentSharedViewModel), ui.getStudentTable());    
        this.studentCreatorPresenter = new StudentCreatorController(new StudentCreationViewModel(studentSharedViewModel), ui.getStudentCreatorPanel());
        this.studentNavigatorPresenter = new StudentNavigatorController(new StudentNavigatorViewModel(studentSharedViewModel), ui.getStudentNavigatorPanel());
    }   

    public void display() {
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JStudentBase.EXIT_ON_CLOSE);
    }
    
}
