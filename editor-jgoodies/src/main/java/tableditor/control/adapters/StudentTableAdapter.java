package tableditor.control.adapters;



import com.jgoodies.binding.adapter.AbstractTableAdapter;

import tableditor.persistence.facade.dto.StudentDTO;

public class StudentTableAdapter extends AbstractTableAdapter<StudentDTO>  {

    private static final String COLUMNS[] = {
        "ID", "First Name", "Last Name", "Prom", "Grade"
    };

    

    public StudentTableAdapter() {
        super(COLUMNS);
    }


    @Override
    public String getColumnName(int columnIndex) {
        return COLUMNS[columnIndex];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudentDTO student = getRow(rowIndex);
        return switch (columnIndex) {
            case 0 -> student.id();
            case 1 -> student.firstname();
            case 2 -> student.lastname();
            case 3 -> student.prom();
            case 4 -> student.grade();
            default -> null;
        };  
    }
    
}
