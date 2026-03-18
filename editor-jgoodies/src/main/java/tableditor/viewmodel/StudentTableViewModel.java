package tableditor.viewmodel;

import java.awt.List;

import javax.swing.ListModel;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ConverterValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.common.collect.ArrayListModel;

import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.viewmodel.adapters.NotNullBindingConverterverter;
import tableditor.viewmodel.adapters.ReadOnlyValueModelDecorator;

public class StudentTableViewModel {
    private StudentSharedViewModel studentSharedViewModel;
    
    private SelectionInList<StudentDTO> studentSelectionInList;
    private ReadOnlyValueModelDecorator hasSelectionModel;

    public StudentTableViewModel(StudentSharedViewModel studentSharedViewModel) {
        this.studentSharedViewModel = studentSharedViewModel;
        this.studentSelectionInList = new SelectionInList<>((ListModel<StudentDTO>) getStudents());


        // Bind hasSelectionModel to the presence of a selection.
        ConverterValueModel converter = new ConverterValueModel(studentSelectionInList.getSelectionHolder(),
                new NotNullBindingConverterverter());
        this.hasSelectionModel = new ReadOnlyValueModelDecorator(converter);
    }

    public ArrayListModel<StudentDTO> getStudents() {
        return studentSharedViewModel.getStudents();
    }

    public SelectionInList<StudentDTO> getStudentSelectionInList() {
       return studentSelectionInList;
    }

    public ValueModel hasSelection() {
        return hasSelectionModel;
    }

      public void deleteSelectedStudent() {
        if (studentSelectionInList.hasSelection()) {
            StudentDTO selected = studentSelectionInList.getSelection();
            StudentPersistenceFacade persistence = studentSharedViewModel.getStudentPersistenceFacade();
            persistence.deleteStudent(selected.id());
            getStudents().remove(selected);
        }
    }

    
}
