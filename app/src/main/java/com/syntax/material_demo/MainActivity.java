package com.syntax.material_demo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
Reference Links
http://android-er.blogspot.in/2014/04/example-of-viewpager-with-custom.html
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    TextView textView_UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Fragment ----- START
        Fragment fr = new MainFragmest();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.nav_contentframe, fr);
        fragmentTransaction.commit();
        //Initial Fragment ----- END

        //Navigation Drawer ----- START
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Closing navigation_drawer_items on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){

                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home Selected",Toast.LENGTH_SHORT).show();
                        Fragment fr = new MainFragmest();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_contentframe, fr);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.notifications:
                        Toast.makeText(getApplicationContext(),"Notifications Selected",Toast.LENGTH_SHORT).show();
                        Fragment fragment_Notifications = new NotificationsFragmest();
                        FragmentManager fragmentManager_notification = getFragmentManager();
                        FragmentTransaction fragmentTransaction_Notification = fragmentManager_notification.beginTransaction();
                        fragmentTransaction_Notification.replace(R.id.nav_contentframe, fragment_Notifications);
                        fragmentTransaction_Notification.commit();
                        return true;

                    case R.id.introduction:
                        Toast.makeText(getApplicationContext(),"Introduction Selected",Toast.LENGTH_SHORT).show();
                        Fragment fragment_Introduction = new IntroductionFragment();
                        FragmentManager fragmentManager_Introduction = getFragmentManager();
                        FragmentTransaction fragmentTransaction_Introduction = fragmentManager_Introduction.beginTransaction();
                        fragmentTransaction_Introduction.replace(R.id.nav_contentframe, fragment_Introduction);
                        fragmentTransaction_Introduction.commit();
                        return true;

                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(),"Drafts Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(),"All Mail Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Trash Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        //Navigation Header
        View headerView = navigationView.inflateHeaderView(R.layout.drawer_header);
//        headerView.findViewById(R.id.navigation_header_text);
        textView_UserName = (TextView) headerView.findViewById(R.id.username);
        textView_UserName.setText("Sala Suresh..");

        ImageView imageView_ProfilePic = (ImageView) headerView.findViewById(R.id.profile_image);
        imageView_ProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.icon));

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the navigation_drawer_items closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the navigation_drawer_items open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to navigation_drawer_items layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        //Navigation Drawer ----- END
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_item:
                showToastmessage("Action bar item clicked");
                break;
            case R.id.action_option_1:
                showToastmessage("Action bar option 1 clicked");
                break;
            case R.id.action_option_2:
                showToastmessage("Action bar option 2 clicked");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Show Toast message
    public void showToastmessage(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
