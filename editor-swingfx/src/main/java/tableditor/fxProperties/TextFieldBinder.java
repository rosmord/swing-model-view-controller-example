package tableditor.fxProperties;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import io.github.parubok.swingfx.beans.binding.DoubleExpression;
import io.github.parubok.swingfx.beans.binding.IntegerExpression;
import io.github.parubok.swingfx.beans.property.DoubleProperty;
import io.github.parubok.swingfx.beans.property.IntegerProperty;
import io.github.parubok.swingfx.beans.property.StringProperty;

public final class TextFieldBinder {

    private TextFieldBinder() {
    }

    public static void bindBidirectional(JTextField textField, StringProperty property) {

        // Property → JTextField
        property.addListener((obs, oldVal, newVal) -> {
            String text = textField.getText();

            if (!extendedEquals(text, newVal)) {
                textField.setText(newVal);
            }
        });

        // JTextField → Property
        textField.getDocument().addDocumentListener(new DocumentListener() {
            private void update() {
                String text = textField.getText();
                String propertyText = property.get();
                // Compare, avoiding infinite loop if they are already the same
                // Handle nulls as well
                if (!extendedEquals(text, propertyText)) {
                    property.set(text);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        // Initial sync
        textField.setText(property.get());
    }

    private static boolean extendedEquals(Object a, Object b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static void bindBidirectional(JFormattedTextField textField, DoubleProperty property) {

        // Property → JTextField
        property.addListener((obs, oldVal, newVal) -> {
            Double oldValue = (Double) textField.getValue();

            if (!extendedEquals(oldValue, newVal)) {
                textField.setValue(newVal);
            }
        });

        // JTextField → Property
        textField.addPropertyChangeListener("value",
                evt -> {
                    Double fieldValue = (Double) textField.getValue();
                    Double propertyValue = property.get();
                    if (!extendedEquals(fieldValue, propertyValue)) {
                        property.set(fieldValue);
                    }
                });
        // Initial sync
        textField.setValue(property.get());
    }

    /**
     * From property to textfield content.
     * 
     * @param textField
     * @param property
     */
    public static void bindFromModel(JTextField textField, StringProperty property) {

        // Property → JTextField
        property.addListener((obs, oldVal, newVal) -> {
            String text = textField.getText();

            if (!extendedEquals(text, newVal)) {
                textField.setText(newVal);
            }
        });

        // Initial sync
        textField.setText(property.get());
    }

    /**
     * From property to textfield content.
     * 
     * @param textField
     * @param property
     */
    public static void bindFromDouble(JFormattedTextField textField, DoubleExpression property) {

        // Property → JTextField
        property.addListener((obs, oldVal, newVal) -> {
            Double oldValue = (Double) textField.getValue();

            if (!extendedEquals(oldValue, newVal)) {
                textField.setValue(newVal);
            }
        });

        // Initial sync
        textField.setValue(property.get());
    }

    /**
     * From property to textfield content.
     * 
     * @param textField
     * @param property
     */
    public static void bindFromInteger(JFormattedTextField textField, IntegerExpression property) {
        // Property → JTextField
        property.addListener((obs, oldVal, newVal) -> {
            Integer oldValue = (Integer) textField.getValue();

            if (!extendedEquals(oldValue, newVal)) {
                textField.setValue(newVal);
            }
        });

        // Initial sync
        textField.setValue(property.get());
    }


}
