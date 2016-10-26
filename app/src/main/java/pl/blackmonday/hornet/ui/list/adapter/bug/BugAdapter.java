package pl.blackmonday.hornet.ui.list.adapter.bug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import pl.blackmonday.hornet.ui.list.data.bug.BugItem;
import pl.blackmonday.hornet.ui.list.data.bug.BugSnapshot;
import pl.blackmonday.hornet.ui.list.data.bug.OnBugClickedListener;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */


public class BugAdapter extends FlexibleAdapter<AbstractFlexibleItem>{

    private OnBugClickedListener listener;

    public BugAdapter(OnBugClickedListener listener) {
        super(Collections.emptyList());
        this.listener = listener;
    }

    public void updateData(List<BugSnapshot> bugs){
        List<AbstractFlexibleItem> bugItems = new ArrayList<>();
        for (BugSnapshot bug : bugs){
            bugItems.add(new BugItem(bug, listener));
        }
        if (bugItems.isEmpty()) bugItems.add(new BugAdapterEmptyItem());

        updateDataSet(bugItems);
        notifyDataSetChanged();
    }

}
