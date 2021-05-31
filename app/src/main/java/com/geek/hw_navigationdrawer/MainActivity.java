package com.geek.hw_navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.geek.hw_navigationdrawer.databinding.ActivityMainBinding;
import com.geek.hw_navigationdrawer.fragments.AllInboxesFragment;
import com.geek.hw_navigationdrawer.fragments.DoneFragment;
import com.geek.hw_navigationdrawer.fragments.DraftsFragment;
import com.geek.hw_navigationdrawer.fragments.HelpFragment;
import com.geek.hw_navigationdrawer.fragments.InboxFragment;
import com.geek.hw_navigationdrawer.fragments.RemindersFragment;
import com.geek.hw_navigationdrawer.fragments.SentFragment;
import com.geek.hw_navigationdrawer.fragments.SettingsFragment;
import com.geek.hw_navigationdrawer.fragments.SnoozedFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close);

        setSupportActionBar(binding.toolbar);
        toggle.syncState();
        binding.drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        addNavView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle != null && toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void addNavView(){
        binding.navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    Fragment fragment = null;
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.all_inboxes:
                                fragment = new AllInboxesFragment();
                                break;
                            case R.id.inbox:
                                fragment = new InboxFragment();
                                break;
                            case R.id.snoozed:
                                fragment = new SnoozedFragment();
                                break;
                            case R.id.done:
                                fragment = new DoneFragment();
                                break;
                            case R.id.drafts:
                                fragment = new DraftsFragment();
                                break;
                            case R.id.sent:
                                fragment = new SentFragment();
                                break;
                            case R.id.reminders:
                                fragment = new RemindersFragment();
                                break;
                            case R.id.settings:
                                fragment = new SettingsFragment();
                                break;
                            case R.id.help:
                                fragment = new HelpFragment();
                                break;
                            default:
                                fragment = new AllInboxesFragment();
                        }

                        selectFragment(fragment);
                        item.setChecked(true);
                        setTitle(item.getTitle());
                        binding.drawerLayout.closeDrawers();
                        return false;
                    }
                }
        );
    }

    private void selectFragment(Fragment fragment){
        FragmentManager mg = getSupportFragmentManager();
        mg.beginTransaction().replace(R.id.container, fragment).commit();
    }
}