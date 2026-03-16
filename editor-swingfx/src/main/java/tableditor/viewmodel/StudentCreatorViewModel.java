package tableditor.viewmodel;

import ca.odell.glazedlists.EventList;
import io.github.parubok.swingfx.beans.property.BooleanProperty;
import io.github.parubok.swingfx.beans.property.DoubleProperty;
import io.github.parubok.swingfx.beans.property.ObjectProperty;
import io.github.parubok.swingfx.beans.property.SimpleBooleanProperty;
import io.github.parubok.swingfx.beans.property.SimpleDoubleProperty;
import io.github.parubok.swingfx.beans.property.SimpleObjectProperty;
import io.github.parubok.swingfx.beans.property.SimpleStringProperty;
import io.github.parubok.swingfx.beans.property.StringProperty;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;

public class StudentCreatorViewModel {

    private StudentPersistenceFacade baseFacade;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<PromotionDTO> selectedPromotion = new SimpleObjectProperty<>();
    private DoubleProperty gradeProperty = new SimpleDoubleProperty();

    private BooleanProperty canSaveProperty;
    private StudentSharedViewModel sharedViewModel;

    public StudentCreatorViewModel(StudentPersistenceFacade baseFacade, StudentSharedViewModel sharedViewModel) {
        this.baseFacade = baseFacade;
        this.sharedViewModel = sharedViewModel;
        this.canSaveProperty = new SimpleBooleanProperty();

        this.canSaveProperty.bind(
                firstName.isNotEmpty()
                        .and(lastName.isNotEmpty())
                        .and(selectedPromotion.isNotNull())
                        .and(gradeProperty.greaterThanOrEqualTo(0.0))
                        .and(gradeProperty.lessThanOrEqualTo(20.0)));
        selectedPromotion.set(promotionList().get(0));

    }

    public StringProperty firstName() {
        return firstName;
    }

    public StringProperty lastName() {
        return lastName;
    }

    public ObjectProperty<PromotionDTO> selectedPromotion() {
        return selectedPromotion;
    }

    public DoubleProperty grade() {
        return gradeProperty;
    }

    public BooleanProperty canSave() {
        return canSaveProperty;
    }

    public EventList<PromotionDTO> promotionList() {
        return sharedViewModel.getPromotions();
    }

    public void saveStudent() {
        StudentDTO studentDTO = new StudentDTO(null, firstName.get(), lastName.get(), selectedPromotion.get().prom(),
                gradeProperty.get());
        StudentDTO saved = baseFacade.saveStudent(studentDTO);
        sharedViewModel.getStudents().add(saved);
        // Clear the form
        firstName.set("");
        lastName.set("");
        selectedPromotion.set(promotionList().get(0));
        gradeProperty.set(0);
    }
}
