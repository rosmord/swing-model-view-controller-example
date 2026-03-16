package tableditor.ui.specs;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public interface IStudentNavigatorPanel {

    JFormattedTextField getIdField();

    JTextField getFirstnameField();

    JTextField getLastnameField();

    JComboBox<String> getPromComboBox();

    JFormattedTextField getGradeField();

    JButton getUpdateButton();

    JButton getNextButton();

    JButton getPreviousButton();

}