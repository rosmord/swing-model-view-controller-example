package tableditor.viewmodel.adapters;

import com.jgoodies.binding.value.BindingConverter;

/**
 * A converter from object to boolean, true iff the object is not null.
 * 
 * <p>This is supposed to be a read-only converter, so the sourceValue method is not implemented.
 * <p>typically, the converted ModelValues should be read-only, either by design, or by using the 
 * {@link ReadOnlyValueModelDecorator}.
 */
public class NotNullBindingConverterverter implements BindingConverter<Object, Boolean> {

    @Override
    public Boolean targetValue(Object sourceValue) {
        return sourceValue != null;
    }

    @Override
    public Object sourceValue(Boolean targetValue) {
        throw new UnsupportedOperationException("NotNullValueModel is a one-way converter");
    }
}
