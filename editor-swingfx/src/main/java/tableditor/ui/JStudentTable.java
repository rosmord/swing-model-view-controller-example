package tableditor.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import net.miginfocom.swing.MigLayout;
import tableditor.ui.specs.IStudentTable;
import tableditor.ui.utils.SwingPanelDisplayer;

/**
 * UI class for displaying and editing students in a table.
 */
public class JStudentTable extends JPanel implements IStudentTable{
    JTable table = new JTable();

    JButton deleteButton = new JButton("Delete");

    public JStudentTable() {
        prepareLayout();
    }

    
    private void prepareLayout() {
        setLayout(new MigLayout());
        add(new JScrollPane(table), "dock center");
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.add(deleteButton);
        add(toolBar, "dock east");
    }

    @Override
    public JTable getTable() {
        return table;
    }


    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }

    public static void main(String[] args) {
       SwingPanelDisplayer.displayPanel(() -> new JStudentTable());
    }
   

}
