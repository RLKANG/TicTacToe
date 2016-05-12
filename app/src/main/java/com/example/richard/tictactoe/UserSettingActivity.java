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

            Preference pref_mode = findPreference("prefUpdateMode");
            int cur_mode = Integer.parseInt(sharedPref.getString("prefUpdateMode", "1"));
            if (cur_mode == 1) {
                pref_mode.setSummary("Player vs. Player");
            } else {
                pref_mode.setSummary("Player vs. Computer");
            }

            Preference pref_level = findPreference("prefUpdateLevel");
            int cur_level = Integer.parseInt(sharedPref.getString("prefUpdateLevel", "1"));
            if (cur_level == 1) {
                pref_level.setSummary("Easy");
            } else if (cur_level == 2) {
                pref_level.setSummary("Medium");
            } else {
                pref_level.setSummary("Hard");
            }

            Preference pref_challenge = findPreference("prefUpdateChallenge");
            int cur_challenge = Integer.parseInt(sharedPref.getString("prefUpdateChallenge", "1"));
            if (cur_challenge == 1) {
                pref_challenge.setSummary("Player goes first");
            } else {
                pref_challenge.setSummary("Computer goes first");
            }
        }

        public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key) {
            Preference pref_mode= findPreference("prefUpdateMode");
            String str_mode;
            int cur_mode= Integer.parseInt(sharedPreferences.getString("prefUpdateMode", "1"));
            if (cur_mode==1) {
                str_mode="Player vs. Player";
            }
            else {
                str_mode="Player vs. Computer";
            }
            pref_mode.setSummary(str_mode);

            Preference pref_level= findPreference("prefUpdateLevel");
            String str_level;
            int cur_level= Integer.parseInt(sharedPreferences.getString("prefUpdateLevel", "1"));
            if (cur_level==1) {
                str_level="Easy";
            }
            else if (cur_level==2) {
                str_level="Medium";
            }
            else {
                str_level="Hard";
            }
            pref_level.setSummary(str_level);

            Preference pref_challenge= findPreference("prefUpdateChallenge");
            String str_challenge;
            int cur_challenge= Integer.parseInt(sharedPreferences.getString("prefUpdateChallenge", "1"));
            if (cur_challenge==1) {
                str_challenge="Player goes first";
            }
            else {
                str_challenge="Computer goes first";
            }
            pref_challenge.setSummary(str_challenge);
        }
    }
}
