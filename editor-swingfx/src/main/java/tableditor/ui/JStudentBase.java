package tableditor.ui;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import tableditor.ui.specs.IStudentBase;
import tableditor.ui.specs.IStudentCreatorPanel;
import tableditor.ui.specs.IStudentNavigatorPanel;
import tableditor.ui.specs.IStudentTable;

public class JStudentBase extends JFrame implements IStudentBase {
    JStudentTable studentTable = new JStudentTable();
    JStudentCreatorPanel studentCreatorPanel = new JStudentCreatorPanel();
    JStudentNavigatorPanel studentNavigatorPanel = new JStudentNavigatorPanel();

    public JStudentBase() {
        prepareLayout();
    }

    private void prepareLayout() {
        setLayout(new MigLayout());
        add(studentTable, "grow, dock center");
        add(studentCreatorPanel, "dock west");
        add(studentNavigatorPanel, "dock east");

        pack();
    }

    @Override
    public IStudentTable getStudentTable() {
        return studentTable;
    }

    public IStudentCreatorPanel getStudentCreatorPanel() {
        return studentCreatorPanel;
    }

    public IStudentNavigatorPanel getStudentNavigatorPanel() {
        return studentNavigatorPanel;
    }
}
