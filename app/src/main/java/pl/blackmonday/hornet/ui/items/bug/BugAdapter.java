package pl.blackmonday.hornet.ui.items.bug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */


public class BugAdapter extends FlexibleAdapter<AbstractFlexibleItem>{

    private BugItem.OnClickListener listener;

    public BugAdapter(BugItem.OnClickListener listener) {
        super(Collections.emptyList());
        this.listener = listener;
    }

    public void updateData(List<Bug> bugs){
        List<AbstractFlexibleItem> bugItems = new ArrayList<>();
        for (Bug bug : bugs){
            bugItems.add(new BugItem(bug, listener));
        }
        if (bugItems.isEmpty()) bugItems.add(new NoBugsItem());

        updateDataSet(bugItems);
        notifyDataSetChanged();
    }

}
