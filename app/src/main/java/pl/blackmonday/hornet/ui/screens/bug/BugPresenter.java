package pl.blackmonday.hornet.ui.screens.bug;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BasePresenter;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public class BugPresenter
        extends BasePresenter<BugUi> {

    //==============================================================================================
    // FIELDS
    //==============================================================================================

    private Bug bug;

    //==============================================================================================
    // CREATION
    //==============================================================================================

    public BugPresenter(BugUi ui, Navigator navigator) {
        super(ui, navigator);
        bug = navigator.getBug();
    }

    //==============================================================================================
    // LIFECYCLE
    //==============================================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        showBug();
    }

    //==============================================================================================
    // PRIVATE METHODS
    //==============================================================================================

    private void showBug() {
        ui.setBugId(bug.getId());
        ui.setBugTitle(bug.getTitle());
        ui.setBugPriority(bug.getPriority());
        ui.setBugCreationDate(bug.getCreationDate());
        ui.setBugDescription(bug.getDescription());
    }

}
