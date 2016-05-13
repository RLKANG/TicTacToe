package com.example.richard.tictactoe;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //PreferenceManager.setDefaultValues(getActivity(), R.xml.user_settings, true);
            addPreferencesFromResource(R.xml.user_settings);
            SharedPreferences sharedPref = getPreferenceScreen().getSharedPreferences();
            sharedPref.registerOnSharedPreferenceChangeListener(this);
            update(sharedPref); //update preferences screen upon loading
        }

        //Listener for preference changes
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            update(sharedPreferences);
        }

        //update preference screen
        public void update(SharedPreferences sharedPref) {

            //get current mode and change summary of mode preference according to it
            Preference pref_mode = findPreference("prefUpdateMode");
            int cur_mode = Integer.parseInt(sharedPref.getString("prefUpdateMode", "1"));
            if (cur_mode == 1) {
                pref_mode.setSummary("Player vs. Player");
            } else {
                pref_mode.setSummary("Player vs. Computer");
            }

            //get current level and change summary of level preference according to it
            Preference pref_level = findPreference("prefUpdateLevel");
            int cur_level = Integer.parseInt(sharedPref.getString("prefUpdateLevel", "1"));
            if (cur_level == 1) {
                pref_level.setSummary("Easy");
            } else if (cur_level == 2) {
                pref_level.setSummary("Medium");
            } else {
                pref_level.setSummary("Hard");
            }

            //get current challenge mode and change summary of challenge mode according to it
            Preference pref_challenge = findPreference("prefUpdateChallenge");
            int cur_challenge = Integer.parseInt(sharedPref.getString("prefUpdateChallenge", "1"));
            if (cur_challenge == 1) {
                pref_challenge.setSummary("Player goes first (OFF)");
            } else {
                pref_challenge.setSummary("Computer goes first (ON)");
            }
        }
    }
}
