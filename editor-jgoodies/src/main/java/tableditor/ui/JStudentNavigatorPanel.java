package tableditor.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import tableditor.ui.specs.IStudentNavigatorPanel;
import tableditor.ui.utils.SwingPanelDisplayer;

/**
 * UI Class for browing students with various controls.
 */
public class JStudentNavigatorPanel extends JPanel implements IStudentNavigatorPanel {
    JFormattedTextField idField = new JFormattedTextField(0);
    JTextField firstnameField = new JTextField(10);
    JTextField lastnameField = new JTextField(10);
    JComboBox<String> promComboBox = new JComboBox<>();
    JFormattedTextField gradeField = new JFormattedTextField(0.0);
    
    JButton updateButton = new JButton("update");
    JButton nextButton = new JButton("next");
    JButton previousButton = new JButton("previous");


    public JStudentNavigatorPanel() {
        idField.setEditable(false);
        prepareLayout();
    }
            
    private void prepareLayout() {
          setLayout(new MigLayout("",
            "[][grow, fill]",
            ""
        ));
        add (new JLabel("ID"), "");
        add(idField, "wrap");
        add(new JLabel("First Name"), "");
        add(firstnameField, "wrap");
        add(new JLabel("Last Name"), "");
        add(lastnameField, "wrap");
        add(new JLabel("Promotion"), "");
        add(promComboBox, "wrap");
        add(new JLabel("Grade"), "");
        add(gradeField, "wrap");
        // Add the buttons in a separate row, with some space before them.
        // add some space between the buttons, to separate the "update" button from the navigation buttons.
        // Keep all buttons the same size        
        add(updateButton, "span 2, split 3, gap unrelated, sizegroup btn");
        add(nextButton, "tag next, sizegroup btn");
        add(previousButton, "tag previous, sizegroup btn");    
    }

    @Override
    public JFormattedTextField getIdField() {
        return idField;
    }



    @Override
    public JTextField getFirstnameField() {
        return firstnameField;
    }



    @Override
    public JTextField getLastnameField() {
        return lastnameField;
    }



    @Override
    public JComboBox<String> getPromComboBox() {
        return promComboBox;
    }



    @Override
    public JFormattedTextField getGradeField() {
        return gradeField;
    }



    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }



    @Override
    public JButton getNextButton() {
        return nextButton;
    }



    @Override
    public JButton getPreviousButton() {
        return previousButton;
    }



    public static void main(String[] args) {
        SwingPanelDisplayer.displayPanel(() -> new JStudentNavigatorPanel());
    }
}
