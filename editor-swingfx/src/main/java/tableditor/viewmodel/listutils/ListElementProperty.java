package tableditor.viewmodel.listutils;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import io.github.parubok.swingfx.beans.property.IntegerProperty;
import io.github.parubok.swingfx.beans.property.ReadOnlyObjectWrapper;

public class ListElementProperty<T> extends ReadOnlyObjectWrapper<T> {

    private final EventList<T> source;
    private IntegerProperty index;

    public ListElementProperty(EventList<T> source, IntegerProperty index) {
        this.source = source;
        this.index = index;
        updateValue();

        source.addListEventListener(new ListEventListener<T>() {
            @Override
            public void listChanged(ListEvent<T> listChanges) {
                updateValue();
            }
        });

        index.addListener(
            (obs, oldValue, newValue) -> {
                updateValue();
            }
        );
    }

    private void updateValue() {
        int idx = index.get();
        if (idx >= 0 && idx < source.size()) {
            set(source.get(idx));
        } else {
            set(null);
        }
    }

}