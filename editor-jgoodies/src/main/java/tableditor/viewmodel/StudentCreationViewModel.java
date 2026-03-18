package tableditor.viewmodel;

import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.common.collect.ArrayListModel;

import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.viewmodel.adapters.AndNotEmptyValueModel;

public class StudentCreationViewModel {

    private final StudentSharedViewModel shareViewModel;
    private ValueModel firstName = new ValueHolder("");
    private ValueModel lastName = new ValueHolder("");
    private ValueModel promotion = new ValueHolder("");
    private ValueModel grade = new ValueHolder(Double.valueOf(0));
    private ValueModel canSave = new AndNotEmptyValueModel(
            firstName, lastName, promotion, grade);

    /**
     * @param studentViewModel
     */
    public StudentCreationViewModel(StudentSharedViewModel studentViewModel) {
        this.shareViewModel = studentViewModel;
    }

    public ArrayListModel<StudentDTO> students() {
        return shareViewModel.getStudents();
    }

    public ArrayListModel<PromotionDTO> promotions() {
        return shareViewModel.getPromotions();
    }

    public ValueModel firstName() {
        return firstName;
    }

    public ValueModel lastName() {
        return lastName;
    }

    public ValueModel promotion() {
        return promotion;
    }

    public ValueModel grade() {
        return grade;
    }

    public ValueModel canSave() {
        return canSave;
    }

    public void doSaveStudent() {
        StudentDTO studentDTO = new StudentDTO(null,
                (String) firstName.getValue(), (String) lastName.getValue(), promotion.getValue().toString(),
                (Double) grade.getValue());
        StudentPersistenceFacade persistence = shareViewModel.getStudentPersistenceFacade();
        StudentDTO newStudent = persistence.saveStudent(studentDTO);
        students().add(newStudent);
        firstName.setValue("");
        lastName.setValue("");
        promotion.setValue(promotions().isEmpty() ? "" : promotions().get(0));
        grade.setValue(Double.valueOf(0));
    }
}