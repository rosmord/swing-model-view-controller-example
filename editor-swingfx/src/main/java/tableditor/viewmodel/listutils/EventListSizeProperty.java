package tableditor.viewmodel.listutils;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import io.github.parubok.swingfx.beans.property.ReadOnlyIntegerWrapper;

public class EventListSizeProperty<T> extends ReadOnlyIntegerWrapper {

    private final EventList<T> source;

    public EventListSizeProperty(EventList<T> source) {
        this.source = source;
        set(source.size()); // initial value

        // listen for changes
        source.addListEventListener(new ListEventListener<T>() {
            @Override
            public void listChanged(ListEvent<T> listChanges) {
                set(source.size());
            }
        });
    }
}