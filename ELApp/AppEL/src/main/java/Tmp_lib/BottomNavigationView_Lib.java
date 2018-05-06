package Tmp_lib;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.example.lenovo.elapp.R;

import Fragments.Fragment_Lib;
import Fragments.MainActivityLeftFragment;
import Fragments.MainActivityRightFragment;

public class BottomNavigationView_Lib {
    /**
     * @param fragmentActivity :the source activity that contains the BottomNavigation
     */
    public static BottomNavigationView.OnNavigationItemSelectedListener
    Get_OnNavigationItemselectedListener(final FragmentActivity fragmentActivity) {
        return item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment_Lib.replaceFragment(fragmentActivity, new MainActivityLeftFragment()
                            , R.id.root_Frame_layout);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    Fragment_Lib.replaceFragment(fragmentActivity, new MainActivityRightFragment()
                            , R.id.root_Frame_layout);
                    return true;
            }
            return false;
        };
    }

}
