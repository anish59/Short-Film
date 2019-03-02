package in.helpingdevelop.shortfilm;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.helpingdevelop.shortfilm.adapters.PagerAdapter;
import in.helpingdevelop.shortfilm.fragments.BookingFragment;
import in.helpingdevelop.shortfilm.fragments.MyProfileFragment;
import in.helpingdevelop.shortfilm.fragments.PaymentFragment;
import in.helpingdevelop.shortfilm.helper.Functions;
import in.helpingdevelop.shortfilm.helper.IntentUtils;
import in.helpingdevelop.shortfilm.helper.PrefUtils;
import in.helpingdevelop.shortfilm.model.UserProfile;

public class NavigationActivity extends AppCompatActivity {

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Set up Tabbed Items*/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Previous"));
        tabLayout.addTab(tabLayout.newTab().setText("Now Running"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*Tabbed items Ends*/

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        TextView closeDrawerBtn = navigationView.getHeaderView(0).findViewById(R.id.close_drawer_icon);
        closeDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawers();
            }
        });

        ListView navigation_item_list = navigationView.findViewById(R.id.lv_nav_item);

        String[] titleArr = new String[]{
                getString(R.string.my_profile),
                getString(R.string.watch_list),
                getString(R.string.rating),
                getString(R.string.contact_us),
                getString(R.string.logout)
        };
        int[] valueArr = {R.drawable.nav_profile,
                R.drawable.nav_watchlist,
                R.drawable.nav_rating,
                R.drawable.nav_contact_us,
                R.drawable.nav_logout};

        ArrayList<Map<String, Object>> itemDataList = new ArrayList<Map<String, Object>>();

        int titleLen = titleArr.length;
        for (int i = 0; i < titleLen; i++) {
            Map<String, Object> listItemMap = new HashMap<String, Object>();
            listItemMap.put("nav_title", titleArr[i]);
            listItemMap.put("nav_icon", valueArr[i]);
            itemDataList.add(listItemMap);
        }

        SimpleAdapter navAdapter = new SimpleAdapter(this, itemDataList, R.layout.navigation_view,
                new String[]{"nav_title", "nav_icon"}, new int[]{R.id.nav_title, R.id.nav_icon});

        navigation_item_list.setAdapter(navAdapter);
        navigation_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main_layout, new MyProfileFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case 1:
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.main_layout, new BookingFragment());
                        ft2.addToBackStack(null);
                        ft2.commit();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4://Logout
                        Functions.doLogOut(NavigationActivity.this);
                        break;

                }

                LinearLayout item = view.findViewById(R.id.nav_item);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    item.setForeground(new ColorDrawable(getColor(R.color.colorAccent)));
                }*/

                drawer.closeDrawer(GravityCompat.START);
            }
        });


    }

    /* public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
         View tabStrip = tabLayout.getChildAt(0);
         if (tabStrip instanceof ViewGroup) {
             ViewGroup tabStripGroup = (ViewGroup) tabStrip;
             int childCount = ((ViewGroup) tabStrip).getChildCount();
             for (int i = 0; i < childCount; i++) {
                 View tabView = tabStripGroup.getChildAt(i);
                 //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                 tabView.setMinimumWidth(0);
                 // set padding to 0 for wrapping indicator as title
                 tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                 // setting custom margin between tabs
                 if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                     ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                     if (i == 0) {
                         // left
                         settingMargin(layoutParams, externalMargin, internalMargin);
                     } else if (i == childCount - 1) {
                         // right
                         settingMargin(layoutParams, internalMargin, externalMargin);
                     } else {
                         // internal
                         settingMargin(layoutParams, internalMargin, internalMargin);
                     }
                 }
             }

             tabLayout.requestLayout();
         }
     }

     private void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
             layoutParams.setMarginStart(start);
             layoutParams.setMarginEnd(end);
             layoutParams.leftMargin = start;
             layoutParams.rightMargin = end;
         } else {
             layoutParams.leftMargin = start;
             layoutParams.rightMargin = end;
         }
     }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
