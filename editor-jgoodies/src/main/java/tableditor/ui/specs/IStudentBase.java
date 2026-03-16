package tableditor.ui.specs;

public interface IStudentBase {

    IStudentTable getStudentTable();
    IStudentCreatorPanel getStudentCreatorPanel();
    IStudentNavigatorPanel getStudentNavigatorPanel();
    
    void setVisible(boolean b);

    void setDefaultCloseOperation(int exitOnClose);

}