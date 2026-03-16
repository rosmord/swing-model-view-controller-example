package tableditor.viewmodel.adapters;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.ValueModel;

/**
 * Decorates a ValueModel to make it read-only.
 * 
 * <p> The getValue method is delegated to the decorated model, but the setValue method does nothing.
 */
public class ReadOnlyValueModelDecorator extends AbstractValueModel{
    ValueModel delegate;
    

    public ReadOnlyValueModelDecorator(ValueModel delegate) {
        this.delegate = delegate;
        this.delegate.addValueChangeListener(evt -> {
            fireValueChange(evt.getOldValue(), evt.getNewValue());            
        });
    }

    @Override
    public Object getValue() {        
        return delegate.getValue();
    }

    @Override
    public void setValue(Object newValue) {
        // Does nothing
    }
    
}
