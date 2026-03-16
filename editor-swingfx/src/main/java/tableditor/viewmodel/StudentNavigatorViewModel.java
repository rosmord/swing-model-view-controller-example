package tableditor.viewmodel;

import ca.odell.glazedlists.EventList;
import io.github.parubok.swingfx.beans.property.BooleanProperty;
import io.github.parubok.swingfx.beans.property.DoubleProperty;
import io.github.parubok.swingfx.beans.property.IntegerProperty;
import io.github.parubok.swingfx.beans.property.ObjectProperty;
import io.github.parubok.swingfx.beans.property.ReadOnlyIntegerProperty;
import io.github.parubok.swingfx.beans.property.ReadOnlyIntegerWrapper;
import io.github.parubok.swingfx.beans.property.SimpleBooleanProperty;
import io.github.parubok.swingfx.beans.property.SimpleDoubleProperty;
import io.github.parubok.swingfx.beans.property.SimpleIntegerProperty;
import io.github.parubok.swingfx.beans.property.SimpleObjectProperty;
import io.github.parubok.swingfx.beans.property.SimpleStringProperty;
import io.github.parubok.swingfx.beans.property.StringProperty;
import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.viewmodel.listutils.EventListSizeProperty;
import tableditor.viewmodel.listutils.ListElementProperty;

/**
 * A view model for navigating between students.
 */
public class StudentNavigatorViewModel {
    private StudentPersistenceFacade baseFacade;
    private StudentSharedViewModel sharedViewModel;

    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<PromotionDTO> selectedPromotion = new SimpleObjectProperty<>();
    private DoubleProperty gradeProperty = new SimpleDoubleProperty();

    private IntegerProperty position = new SimpleIntegerProperty(0);

    private BooleanProperty notEmpty = new SimpleBooleanProperty();
    private BooleanProperty hasNext = new SimpleBooleanProperty();
    private BooleanProperty hasPrevious = new SimpleBooleanProperty();
    private BooleanProperty canUpdate = new SimpleBooleanProperty();

    private ReadOnlyIntegerWrapper studentCount;
    private ListElementProperty<StudentDTO> currentStudent;


    public StudentNavigatorViewModel(StudentPersistenceFacade baseFacade, StudentSharedViewModel sharedViewModel) {
        this.baseFacade = baseFacade;
        this.sharedViewModel = sharedViewModel;
        studentCount = new EventListSizeProperty<>(sharedViewModel.getStudents());
        currentStudent = new ListElementProperty<>(sharedViewModel.getStudents(), position);
        notEmpty.bind(studentCount.greaterThan(0));
        hasPrevious.bind(position.greaterThan(0));
        hasNext.bind(position.lessThan(studentCount.subtract(1)));
        
        currentStudent.addListener((o, oldValue, newValue) -> {
            if (newValue != null) {
                id.set(newValue.id());
                firstName.set(newValue.firstname());
                lastName.set(newValue.lastname());
                selectedPromotion.set(new PromotionDTO(newValue.prom()));
                gradeProperty.set(newValue.grade());
            } else {
                id.set(0);
                firstName.set("");
                lastName.set("");
                selectedPromotion.set(null);
                gradeProperty.set(0);
            }
        });
    }

    public ReadOnlyIntegerProperty id() {
        return id.getReadOnlyProperty();
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

    public IntegerProperty position() {
        return position;
    }

    public BooleanProperty hasNext() {
        return hasNext;
    }

    public BooleanProperty hasPrevious() {
        return hasPrevious;
    }

    public BooleanProperty canUpdate() {
        return canUpdate;
    }

    public EventList<PromotionDTO> promotionList() {
        return sharedViewModel.getPromotions();
    }   
    

    public void next() {
        if (hasNext.get()) {
            position.set(position.get() + 1);
        }
    }
    
    public void previous() {
        if (hasPrevious.get()) {
            position.set(position.get() - 1);
        }
    }

}
