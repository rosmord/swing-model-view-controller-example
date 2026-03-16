package tableditor.control.adapters;

import ca.odell.glazedlists.gui.TableFormat;
import tableditor.persistence.facade.dto.StudentDTO;

public class StudentTableFormat implements TableFormat<StudentDTO> {

    private static String [] columnNames = {"ID", "First Name", "Last Name", "Promotion", "Grade"};    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getColumnValue(StudentDTO baseObject, int column) {
        return switch (column) {
            case 0 -> baseObject.id();
            case 1 -> baseObject.firstname();
            case 2 -> baseObject.lastname();
            case 3 -> baseObject.prom();
            case 4 -> baseObject.grade();
            default -> null;
        };
    }


    
}
