package tableditor.control;

import tableditor.ui.JStudentBase;
import tableditor.ui.specs.IStudentBase;
import tableditor.viewmodel.StudentViewModel;

public class StudentBasePresenter {

    private StudentViewModel persistenceLogic;
    private IStudentBase ui = new JStudentBase();
    private StudentTablePresenter tablePresenter;
    private StudentCreatorPresenter studentCreatorPresenter;
    private StudentNavigatorPresenter studentNavigatorPresenter;

    public StudentBasePresenter(StudentViewModel persistenceLogic) {        
        this.persistenceLogic = persistenceLogic;
        this.tablePresenter = new StudentTablePresenter(persistenceLogic, ui.getStudentTable());    
        this.studentCreatorPresenter = new StudentCreatorPresenter(persistenceLogic, ui.getStudentCreatorPanel());
        this.studentNavigatorPresenter = new StudentNavigatorPresenter(persistenceLogic, ui.getStudentNavigatorPanel());
    }   

    public void display() {
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JStudentBase.EXIT_ON_CLOSE);
    }
    
}
