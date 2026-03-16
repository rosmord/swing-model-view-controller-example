package tableditor.viewmodel.adapters;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.List;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.ValueModel;

/**
 * A Value Model that checks if a number of other (String-valued) ValueModels are not Empty.
 * 
 * <p> Note : we could consider a more atomic approach 
 * and writing an AndValueModel that would take boolean-valued ValueModels, 
 * and then combine them.
 */
public class AndNotEmptyValueModel extends AbstractValueModel {

    private List<ValueModel> delegates;
    private boolean currentValue;

    public AndNotEmptyValueModel(ValueModel... delegates) {
        this.delegates = List.copyOf(Arrays.asList(delegates));
        // listen to them and fire a change event if needed.
        for (ValueModel delegate : delegates) {
            delegate.addValueChangeListener(evt -> {
                handleDelegateChange(evt);
            });
        }
        // initial value.
        this.currentValue = getValue();
    }

    private void handleDelegateChange(PropertyChangeEvent evt) {
        boolean newValue = getValue();
        if (newValue != currentValue) {
            fireValueChange(currentValue, newValue);
            currentValue = newValue;
        }
    }

    @Override
    public Boolean getValue() {
        for (ValueModel delegate : delegates) {
            Object value = delegate.getValue();
            if (value == null) {
                return false;
            } else {
                String strValue = value.toString();
                if (strValue.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setValue(Object newValue) {
        // Does nothing, this is a read-only model.
    }
}
