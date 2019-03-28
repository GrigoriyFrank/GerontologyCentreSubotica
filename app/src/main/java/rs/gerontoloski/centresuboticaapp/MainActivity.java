package rs.gerontoloski.centresuboticaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import rs.gerontoloski.centresuboticaapp.fragments.PagesFragment;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Grigoriy Frank
 */

public class MainActivity extends AppCompatActivity {

    private int drawerMenuItemId;
    private boolean showEmailSendButton;
    private int language_id;

    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    private TextView drawerHeaderTitle;
    private TextView footerAppFunds;
    private TextView footerAppViews;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerMenuItemId = Constants.HOME_MENU_ITEM_POSITION;
        showEmailSendButton = false;

        //Drawer menu items
        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(Constants.HOME_MENU_ITEM_POSITION).withName(R.string.home_serbian).withIcon(R.drawable.ic_menu_home).withIconTintingEnabled(true);
        PrimaryDrawerItem aboutUs = new PrimaryDrawerItem().withIdentifier(Constants.ABOUT_US_MENU_ITEM_POSITION).withName(R.string.about_us_serbian).withIcon(R.drawable.ic_menu_about_us).withIconTintingEnabled(true);
        PrimaryDrawerItem activities = new PrimaryDrawerItem().withIdentifier(Constants.ACTIVITIES_MENU_ITEM_POSITION).withName(R.string.activities_serbian).withIcon(R.drawable.ic_activities).withIconTintingEnabled(true);
        PrimaryDrawerItem services = new PrimaryDrawerItem().withIdentifier(Constants.SERVICES_MENU_ITEM_POSITION).withName(R.string.services_serbian).withIcon(R.drawable.ic_services).withIconTintingEnabled(true);
        PrimaryDrawerItem residentialHomes = new PrimaryDrawerItem().withIdentifier(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION).withName(R.string.residential_homes_serbian).withIcon(R.drawable.ic_residental_homes).withIconTintingEnabled(true);
        PrimaryDrawerItem map = new PrimaryDrawerItem().withIdentifier(Constants.MAP_MENU_ITEM_POSITION).withName(R.string.map_serbian).withIcon(R.drawable.ic_menu_map).withIconTintingEnabled(true);
        PrimaryDrawerItem contact = new PrimaryDrawerItem().withIdentifier(Constants.CONTACT_MENU_ITEM_POSITION).withName(R.string.contact_serbian).withIcon(R.drawable.ic_menu_contact).withIconTintingEnabled(true);

        // drawer: START
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.nav_header_main)
                .addDrawerItems(home, aboutUs, activities, services, residentialHomes, map, contact)
                .withFooter(R.layout.nav_footer_main)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerMenuItemId == position) {
                            drawer.closeDrawer();
                            return true;
                        }
                        switch (position) {

                            case Constants.HOME_MENU_ITEM_POSITION:

                                showFragment(Constants.HOME_MENU_ITEM_POSITION, false);
                                drawerMenuItemId = Constants.HOME_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.HOME_MENU_ITEM_POSITION);
                                showEmailSendButton = false;
                                break;

                            case Constants.ABOUT_US_MENU_ITEM_POSITION:

                                showFragment(Constants.ABOUT_US_MENU_ITEM_POSITION, false);
                                drawerMenuItemId = Constants.ABOUT_US_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.ABOUT_US_MENU_ITEM_POSITION);
                                showEmailSendButton = false;
                                break;

                            case Constants.ACTIVITIES_MENU_ITEM_POSITION:

                                showFragment(Constants.ACTIVITIES_MENU_ITEM_POSITION, false);
                                drawerMenuItemId = Constants.ACTIVITIES_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.ACTIVITIES_MENU_ITEM_POSITION);
                                showEmailSendButton = false;
                                break;

                            case Constants.SERVICES_MENU_ITEM_POSITION:

                                showFragment(Constants.SERVICES_MENU_ITEM_POSITION, false);
                                drawerMenuItemId = Constants.SERVICES_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.SERVICES_MENU_ITEM_POSITION);
                                showEmailSendButton = false;
                                break;

                            case Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION:

                                showFragment(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION, false);
                                drawerMenuItemId = Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION);
                                showEmailSendButton = false;
                                break;

                            case Constants.MAP_MENU_ITEM_POSITION:

                                showMap(Uri.parse(Constants.MAP_URL));
                                drawerMenuItemId = Constants.MAP_MENU_ITEM_POSITION;
                                break;

                            case Constants.CONTACT_MENU_ITEM_POSITION:

                                showFragment(Constants.CONTACT_MENU_ITEM_POSITION, true);
                                drawerMenuItemId = Constants.CONTACT_MENU_ITEM_POSITION;
                                setTitleOfActivityAfterLanguageChanged(loadPreferences(),Constants.CONTACT_MENU_ITEM_POSITION);
                                showEmailSendButton = true;
                                break;

                            default:
                                break;

                        }

                        drawer.closeDrawer();
                        return true;
                    }
                })
                .withDelayOnDrawerClose(1500)
                .build();
        //drawer: END

        drawerHeaderTitle = drawer.getHeader().getRootView().findViewById(R.id.drawer_header_title);
        footerAppFunds = drawer.getFooter().getRootView().findViewById(R.id.footer_app_funds);
        footerAppViews = drawer.getFooter().getRootView().findViewById(R.id.footer_app_views);

        setTitlesForDrawer(loadPreferences());
        setTitleOfActivityAfterLanguageChanged(loadPreferences(), drawerMenuItemId);

        showFragment(drawerMenuItemId, showEmailSendButton);


    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Loads preferences for language
     *
     * @return language number chosen by user
     */

    private int loadPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(
                Constants.APP_PREFERENCES, MODE_PRIVATE);

       // Log.i("MainActivity", "language = " + sharedPreferences.getInt(Constants.LANGUAGE, Constants.LANGUAGE_SERBIAN));

        return sharedPreferences.getInt(Constants.LANGUAGE, Constants.LANGUAGE_SERBIAN);

    }

    /**
     * Saves preferences for language
     *
     * @param value language number chosen by user
     */
    private void savePreferences(int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                Constants.APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.LANGUAGE, value);
        editor.apply();
    }

    /**
     * Shows the map
     *
     * @param geoLocation
     */

    private void showMap(Uri geoLocation) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    /**
     * Attach a fragment to this Activity
     *
     * @param drawerMenuItemId          selected drawer menu item
     * @param showEmailSendButton       show or not the email send button
     */

    private void showFragment(int drawerMenuItemId, boolean showEmailSendButton) {

        Fragment fragment = PagesFragment.newInstance(loadPreferences(), drawerMenuItemId, showEmailSendButton);

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();

        }

    }

    /**
     * Changes the language. Just an android:onClick implementation. See layout
     *
     * @param view selected language view
     */

    public void changeLanguage(View view) {

        if (language_id == view.getId()) {

            drawer.closeDrawer();
            return;
        }

        language_id = view.getId();

        switch (language_id) {

            case R.id.serbian:
                savePreferences(Constants.LANGUAGE_SERBIAN);
                setTitlesForDrawer(Constants.LANGUAGE_SERBIAN);
                setTitleOfActivityAfterLanguageChanged(Constants.LANGUAGE_SERBIAN, drawerMenuItemId);
                showFragment(drawerMenuItemId, showEmailSendButton);

                // Toast.makeText(this, "serbian", Toast.LENGTH_SHORT).show();
                break;

            case R.id.english:
                savePreferences(Constants.LANGUAGE_ENGLISH);
                setTitlesForDrawer(Constants.LANGUAGE_ENGLISH);
                setTitleOfActivityAfterLanguageChanged(Constants.LANGUAGE_ENGLISH, drawerMenuItemId);
                showFragment(drawerMenuItemId, showEmailSendButton);

                // Toast.makeText(this, "english", Toast.LENGTH_SHORT).show();
                break;

            case R.id.hungarian:
                savePreferences(Constants.LANGUAGE_HUNGARIAN);
                setTitlesForDrawer(Constants.LANGUAGE_HUNGARIAN);
                setTitleOfActivityAfterLanguageChanged(Constants.LANGUAGE_HUNGARIAN, drawerMenuItemId);
                showFragment(drawerMenuItemId, showEmailSendButton);

                // Toast.makeText(this, "hungarian", Toast.LENGTH_SHORT).show();
                break;

            case R.id.romana:
                savePreferences(Constants.LANGUAGE_ROMANA);
                setTitlesForDrawer(Constants.LANGUAGE_ROMANA);
                setTitleOfActivityAfterLanguageChanged(Constants.LANGUAGE_ROMANA, drawerMenuItemId);
                showFragment(drawerMenuItemId, showEmailSendButton);
                //Toast.makeText(this, "romana", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Unknown language", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer();


    }

    /**
     * Sets all texts in a drawer
     *
     * @param language
     */

    private void setTitlesForDrawer(int language) {


        switch (language) {

            case Constants.LANGUAGE_SERBIAN:

                drawer.updateName(Constants.HOME_MENU_ITEM_POSITION, new StringHolder(R.string.home_serbian));
                drawer.updateName(Constants.ABOUT_US_MENU_ITEM_POSITION, new StringHolder(R.string.about_us_serbian));
                drawer.updateName(Constants.ACTIVITIES_MENU_ITEM_POSITION, new StringHolder(R.string.activities_serbian));
                drawer.updateName(Constants.SERVICES_MENU_ITEM_POSITION, new StringHolder(R.string.services_serbian));
                drawer.updateName(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION, new StringHolder(R.string.residential_homes_serbian));
                drawer.updateName(Constants.MAP_MENU_ITEM_POSITION, new StringHolder(R.string.map_serbian));
                drawer.updateName(Constants.CONTACT_MENU_ITEM_POSITION, new StringHolder(R.string.contact_serbian));
                drawerHeaderTitle.setText(R.string.logo_title_serbian);
                footerAppFunds.setText(R.string.hamburger_footer_app_funds_serbian);
                footerAppViews.setText(R.string.hamburger_footer_app_views_serbian);
                break;

            case Constants.LANGUAGE_ENGLISH:

                drawer.updateName(Constants.HOME_MENU_ITEM_POSITION, new StringHolder(R.string.home_english));
                drawer.updateName(Constants.ABOUT_US_MENU_ITEM_POSITION, new StringHolder(R.string.about_us_english));
                drawer.updateName(Constants.ACTIVITIES_MENU_ITEM_POSITION, new StringHolder(R.string.activities_english));
                drawer.updateName(Constants.SERVICES_MENU_ITEM_POSITION, new StringHolder(R.string.services_english));
                drawer.updateName(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION, new StringHolder(R.string.residential_homes_english));
                drawer.updateName(Constants.MAP_MENU_ITEM_POSITION, new StringHolder(R.string.map_english));
                drawer.updateName(Constants.CONTACT_MENU_ITEM_POSITION, new StringHolder(R.string.contact_english));
                drawerHeaderTitle.setText(R.string.logo_title_english);
                footerAppFunds.setText(R.string.hamburger_footer_app_funds_english);
                footerAppViews.setText(R.string.hamburger_footer_app_views_english);
                break;

            case Constants.LANGUAGE_HUNGARIAN:

                drawer.updateName(Constants.HOME_MENU_ITEM_POSITION, new StringHolder(R.string.home_hungarian));
                drawer.updateName(Constants.ABOUT_US_MENU_ITEM_POSITION, new StringHolder(R.string.about_us_hungarian));
                drawer.updateName(Constants.ACTIVITIES_MENU_ITEM_POSITION, new StringHolder(R.string.activities_hungarian));
                drawer.updateName(Constants.SERVICES_MENU_ITEM_POSITION, new StringHolder(R.string.services_hungarian));
                drawer.updateName(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION, new StringHolder(R.string.residential_homes_hungarian));
                drawer.updateName(Constants.MAP_MENU_ITEM_POSITION, new StringHolder(R.string.map_hungarian));
                drawer.updateName(Constants.CONTACT_MENU_ITEM_POSITION, new StringHolder(R.string.contact_hungarian));
                drawerHeaderTitle.setText(R.string.logo_title_hungarian);
                footerAppFunds.setText(R.string.hamburger_footer_app_funds_hungarian);
                footerAppViews.setText(R.string.hamburger_footer_app_views_hungarian);
                break;

            case Constants.LANGUAGE_ROMANA:

                drawer.updateName(Constants.HOME_MENU_ITEM_POSITION, new StringHolder(R.string.home_romana));
                drawer.updateName(Constants.ABOUT_US_MENU_ITEM_POSITION, new StringHolder(R.string.about_us_romana));
                drawer.updateName(Constants.ACTIVITIES_MENU_ITEM_POSITION, new StringHolder(R.string.activities_romana));
                drawer.updateName(Constants.SERVICES_MENU_ITEM_POSITION, new StringHolder(R.string.services_romana));
                drawer.updateName(Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION, new StringHolder(R.string.residential_homes_romana));
                drawer.updateName(Constants.MAP_MENU_ITEM_POSITION, new StringHolder(R.string.map_romana));
                drawer.updateName(Constants.CONTACT_MENU_ITEM_POSITION, new StringHolder(R.string.contact_romana));
                drawerHeaderTitle.setText(R.string.logo_title_romana);
                footerAppFunds.setText(R.string.hamburger_footer_app_funds_romana);
                footerAppViews.setText(R.string.hamburger_footer_app_views_romana);
                break;

            default:
                Toast.makeText(this, "Unknown language", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    /**
     * Start social. Just an android:onClick implementation. See layout
     *
     * @param view selected social view
     */

    public void startSocial(View view) {

        int social_id = view.getId();

        Intent intent = new Intent(Intent.ACTION_VIEW);

        switch (social_id) {

            case R.id.facebook:

                intent.setData(Uri.parse(Constants.FACEBOOK_URL));
                startActivity(intent);
                break;

            case R.id.twitter:
                intent.setData(Uri.parse(Constants.TWITTER_URL));
                startActivity(intent);
                break;

            case R.id.instagram:
                intent.setData(Uri.parse(Constants.INSTAGRAM_URL));
                startActivity(intent);
                break;

            case R.id.youtube:
                intent.setData(Uri.parse(Constants.YOUTUBE_URL));
                startActivity(intent);
                break;

            default:
                break;

        }

    }

    /**
     * Changes the title of this Activity
     *
     * @param language
     * @param drawerMenuItemId
     */

    private void setTitleOfActivityAfterLanguageChanged(int language, int drawerMenuItemId) {

        switch (language) {

            case Constants.LANGUAGE_SERBIAN:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    setTitle(R.string.home_serbian);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    setTitle(R.string.about_us_serbian);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    setTitle(R.string.activities_serbian);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    setTitle(R.string.services_serbian);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    setTitle(R.string.residential_homes_serbian);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    setTitle(R.string.contact_serbian);

                }

                break;

            case Constants.LANGUAGE_ENGLISH:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    setTitle(R.string.home_english);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    setTitle(R.string.about_us_english);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    setTitle(R.string.activities_english);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    setTitle(R.string.services_english);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    setTitle(R.string.residential_homes_english);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    setTitle(R.string.contact_english);

                }

                break;

            case Constants.LANGUAGE_HUNGARIAN:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    setTitle(R.string.home_hungarian);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    setTitle(R.string.about_us_hungarian);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    setTitle(R.string.activities_hungarian);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    setTitle(R.string.services_hungarian);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    setTitle(R.string.residential_homes_hungarian);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    setTitle(R.string.contact_hungarian);

                }

                break;

            case Constants.LANGUAGE_ROMANA:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    setTitle(R.string.home_romana);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    setTitle(R.string.about_us_romana);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    setTitle(R.string.activities_romana);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    setTitle(R.string.services_romana);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    setTitle(R.string.residential_homes_romana);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    setTitle(R.string.contact_romana);

                }

                break;

            default:
                Toast.makeText(this, "Undefined language", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
