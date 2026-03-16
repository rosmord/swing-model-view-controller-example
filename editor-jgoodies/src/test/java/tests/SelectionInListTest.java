package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.swing.ListModel;

import org.junit.jupiter.api.Test;

import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.common.collect.ArrayListModel;

import tableditor.persistence.facade.dto.StudentDTO;

public class SelectionInListTest {
    @Test
    public void testHolder() {        
        ListModel<StudentDTO> students = new ArrayListModel<StudentDTO>(
            List.of(
                new StudentDTO(1, "Alice", "Smith", "20", 20.0),
                new StudentDTO(2, "Bob", "Johnson", "22", 15.3),
                new StudentDTO(3, "Charlie", "Williams", "21", 2.0)
            )
        );
        ValueHolder selectionHolder = new ValueHolder();
        SelectionInList<StudentDTO> selectionInList = new SelectionInList<>(students, selectionHolder);
        selectionInList.setSelectionIndex(1);
        // Observable selection boolean holder for propert PROPERTY_SELECTION_EMPTY
        PropertyAdapter<SelectionInList> selectionEmptyHolder = new PropertyAdapter<SelectionInList>(selectionInList, 
            SelectionInList.PROPERTY_SELECTION_EMPTY, true);

        assertEquals(new StudentDTO(2, "Bob", "Johnson", "22", 15.3), selectionInList.getSelection());
        assertFalse(selectionEmptyHolder.booleanValue());
        // Delete first element and check selection
        selectionInList.getList().remove(0);
        assertEquals(new StudentDTO(2, "Bob", "Johnson", "22", 15.3), selectionInList.getSelection());
        assertEquals(0, selectionInList.getSelectionIndex());
        assertFalse(selectionEmptyHolder.booleanValue());
        // Delete first element and check selection
        selectionInList.getList().remove(0);
        assertTrue(selectionEmptyHolder.booleanValue());        
    }

}
