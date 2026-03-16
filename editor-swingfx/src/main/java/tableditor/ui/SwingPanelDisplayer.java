package tableditor.ui;

import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingPanelDisplayer {
    public static void displayPanel(Supplier<JComponent> supplier) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.add(supplier.get());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
