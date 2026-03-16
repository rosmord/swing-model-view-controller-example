package tableditor.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.ui.specs.IStudentCreatorPanel;
import tableditor.ui.utils.SwingPanelDisplayer;


public class JStudentCreatorPanel extends JPanel implements  IStudentCreatorPanel {
     // Fields for creating new student
    JTextField firstnameField = new JTextField(10);
    JTextField lastnameField = new JTextField(10);
    JComboBox<PromotionDTO> promComboBox = new JComboBox<>();
    JFormattedTextField gradeField = new JFormattedTextField(Double.valueOf(0));
    JButton saveButton = new JButton("Save");

    public JStudentCreatorPanel() {
        prepareLayout();
    }
    
    
    
    private void prepareLayout() {
        setBorder(BorderFactory.createTitledBorder("New Student"));
        setLayout(new MigLayout("",
            "[][grow, fill]",
            ""
        ));
        add(new JLabel("First Name"), "");
        add(firstnameField, "wrap");
        add(new JLabel("Last Name"), "");
        add(lastnameField, "wrap");
        add(new JLabel("Promotion"), "");
        add(promComboBox, "wrap");
        add(new JLabel("Grade"), "");
        add(gradeField, "wrap");
        add(saveButton, "span 2, tag ok");
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
    public JComboBox<PromotionDTO> getPromComboBox() {
        return promComboBox;
    }

    @Override
    public JFormattedTextField getGradeField() {
        return gradeField;
    }

    @Override
    public JButton getSaveButton() {
        return saveButton;
    }

    public static void main(String[] args) {
        SwingPanelDisplayer.displayPanel(() -> new JStudentCreatorPanel());
    }
}
