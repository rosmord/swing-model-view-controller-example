package tableditor.viewmodel;

import java.util.List;
import java.util.function.Function;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.ConverterValueModel;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.common.collect.ArrayListModel;

import tableditor.persistence.facade.StudentPersistenceFacade;
import tableditor.persistence.facade.dto.PromotionDTO;
import tableditor.persistence.facade.dto.StudentDTO;
import tableditor.viewmodel.adapters.AndNotEmptyValueModel;
import tableditor.viewmodel.adapters.NotNullBindingConverterverter;
import tableditor.viewmodel.adapters.ReadOnlyValueModelDecorator;

/**
 * A global view model shared by all our views.
 * 
 * This might not be such a good idea, but this is also somehow a tech demo.
 * 
 * We might also consider having :
 * - a bunch of local view models for the content of each view ;
 * - a global view model for shared data (the lists, mainly).
 */
public class StudentViewModel {

    /**
     * List of all students.
     */
    private ArrayListModel<StudentDTO> students;

    /**
     * List of all possible promotions.
     */

    private ArrayListModel<PromotionDTO> promotions;

    private SelectionInList<StudentDTO> studentSelectionInList;

    private ReadOnlyValueModelDecorator hasSelectionModel;

    private StudentCreationViewModel studentCreationViewModel;

    private StudentNavigatorViewModel studentNavigatorViewModel;

    private StudentPersistenceFacade studentPersistenceFacade;

    public StudentViewModel(StudentPersistenceFacade studentPersistenceFacade) {
        this.studentPersistenceFacade = studentPersistenceFacade;
        this.students = new ArrayListModel<>(studentPersistenceFacade.findAllStudents());
        this.promotions = new ArrayListModel<>(studentPersistenceFacade.findAllPromotions());
        this.studentSelectionInList = new SelectionInList<>((ListModel<StudentDTO>) students);

        // Bind hasSelectionModel to the presence of a selection.
        ConverterValueModel converter = new ConverterValueModel(studentSelectionInList.getSelectionHolder(),
                new NotNullBindingConverterverter());
        this.hasSelectionModel = new ReadOnlyValueModelDecorator(converter);
        this.studentCreationViewModel = new StudentCreationViewModel();
        this.studentNavigatorViewModel = new StudentNavigatorViewModel();
    }

    public ArrayListModel<StudentDTO> getStudents() {
        return students;
    }

    public ArrayListModel<PromotionDTO> getPromotions() {
        return promotions;
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
            studentPersistenceFacade.deleteStudent(selected.id());
            students.remove(selected);
        }
    }

    public StudentCreationViewModel getStudentCreationViewModel() {
        return studentCreationViewModel;
    }

    public StudentNavigatorViewModel getStudentNavigatorViewModel() {
        return studentNavigatorViewModel;
    }

    public class StudentCreationViewModel {
        private ValueModel firstName = new ValueHolder("");
        private ValueModel lastName = new ValueHolder("");
        private ValueModel promotion = new ValueHolder("");
        private ValueModel grade = new ValueHolder(Double.valueOf(0));
        private ValueModel canSave = new AndNotEmptyValueModel(
                firstName, lastName, promotion, grade);

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
            StudentDTO newStudent = studentPersistenceFacade.saveStudent(studentDTO);
            students.add(newStudent);
            firstName.setValue("");
            lastName.setValue("");
            promotion.setValue(promotions.isEmpty() ? "" : promotions.get(0));
            grade.setValue(Double.valueOf(0));
        }
    }

    public class StudentNavigatorViewModel {
        private ValueModel position = new ValueHolder(0);
        private ValueModel id = new ValueHolder();
        private ValueModel firstName = new ValueHolder("");
        private ValueModel lastName = new ValueHolder("");
        private ValueModel promotion = new ValueHolder("");
        private ValueModel grade = new ValueHolder(Double.valueOf(0));
        private ValueModel canUpdate = new AndNotEmptyValueModel(
                id, firstName, lastName, promotion, grade);
        private ValueModel canNext;
        private ValueModel canPrevious;

        public StudentNavigatorViewModel() {
            loadCurrentStudent();
            Function<List<ValueModel>, Boolean> canNextFunction = (List<ValueModel> l) -> {
                int pos = (Integer) position.getValue();
                return pos < students.size() - 1;
            };
            canNext = new CanNextValueModel();
            canPrevious = new CanPreviousValueModel();
            students.addListDataListener(new ListDataListener() {

                @Override
                public void intervalAdded(ListDataEvent e) {
                    loadCurrentStudent();
                }

                @Override
                public void intervalRemoved(ListDataEvent e) {
                    loadCurrentStudent();
                }

                @Override
                public void contentsChanged(ListDataEvent e) {
                    loadCurrentStudent();
                }
                
            });
        }

        public ValueModel id() {
            return id;
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

        public ValueModel canUpdate() {
            return canUpdate;
        }

        public ValueModel canNext() {
            return canNext;
        }

        public ValueModel canPrevious() {
            return canPrevious;
        }

        public void next() {
            int pos = (Integer) position.getValue();
            if (pos < students.size() - 1) {
                position.setValue(pos + 1);
                loadCurrentStudent();
            } else {
                position.setValue(students.size() - 1);
            }
            loadCurrentStudent();
        }

        public void previous() {
            int pos = (Integer) position.getValue();
            if (pos > 0) {
                position.setValue(pos - 1);
                loadCurrentStudent();
            } else {
                position.setValue(0);
            }
            loadCurrentStudent();
        }

        private void loadCurrentStudent() {
            int pos = (Integer) position.getValue();
            if (pos >= 0 && pos < students.size()) {
                StudentDTO current = students.get(pos);
                id.setValue(current.id());
                firstName.setValue(current.firstname());
                lastName.setValue(current.lastname());
                promotion.setValue(current.prom());
                grade.setValue(current.grade());
            } else {
                id.setValue(null);
                firstName.setValue("");
                lastName.setValue("");
                promotion.setValue("");
                grade.setValue(Double.valueOf(0));
            }
        }

        private class CanNextValueModel extends AbstractValueModel {

            boolean currentValue;

            public CanNextValueModel() {
                position.addValueChangeListener(evt -> {
                    updateValue();
                });

                students.addListDataListener(new ListDataListener() {

                    @Override
                    public void intervalAdded(ListDataEvent e) {
                        updateValue();
                    }

                    @Override
                    public void intervalRemoved(ListDataEvent e) {
                        updateValue();
                    }

                    @Override
                    public void contentsChanged(ListDataEvent e) {
                        updateValue();
                    }

                });
                updateValue();
            }

            private boolean computeValue() {
                int pos = (Integer) position.getValue();
                return pos < students.size() - 1;
            }

            private void updateValue() {
                boolean oldValue = currentValue;
                boolean newValue = computeValue();
                if (oldValue != newValue) {
                    currentValue = newValue;
                    fireValueChange(oldValue, newValue);
                }
            }

            @Override
            public Object getValue() {
                return currentValue;
            }

            @Override
            public void setValue(Object newValue) {
                // Does nothing, this is a read-only model.
            }
        }


        private class CanPreviousValueModel extends AbstractValueModel {

            boolean currentValue;

            public CanPreviousValueModel() {
                position.addValueChangeListener(evt -> {
                    updateValue();
                });

                students.addListDataListener(new ListDataListener() {

                    @Override
                    public void intervalAdded(ListDataEvent e) {
                        updateValue();
                    }

                    @Override
                    public void intervalRemoved(ListDataEvent e) {
                        updateValue();
                    }

                    @Override
                    public void contentsChanged(ListDataEvent e) {
                        updateValue();
                    }

                });
                updateValue();
            }

            private boolean computeValue() {
                int pos = (Integer) position.getValue();
                return pos > 0;
            }

            private void updateValue() {
                boolean oldValue = currentValue;
                boolean newValue = computeValue();
                if (oldValue != newValue) {
                    currentValue = newValue;
                    fireValueChange(oldValue, newValue);
                }
            }

            @Override
            public Object getValue() {
                return currentValue;
            }

            @Override
            public void setValue(Object newValue) {
                // Does nothing, this is a read-only model.
            }
        }


        public void update() {
            if (id.getValue() != null) {
                StudentDTO studentDTO = new StudentDTO((Integer) id.getValue(),
                        (String) firstName.getValue(), (String) lastName.getValue(), promotion.getValue().toString(),
                        (Double) grade.getValue());
                StudentDTO updatedStudent = studentPersistenceFacade.updateStudent(studentDTO);
                int pos = (Integer) position.getValue();
                students.set(pos, updatedStudent);
            }
        }
    }

}
