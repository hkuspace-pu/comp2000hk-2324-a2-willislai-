package comp2000hk.cw2.seasiderestaurant.ui.preference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;


import comp2000hk.cw2.seasiderestaurant.R;
import comp2000hk.cw2.seasiderestaurant.databinding.FragmentPreferenceBinding;

public class PreferenceFragment extends PreferenceFragmentCompat {

    private FragmentPreferenceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PreferenceViewModel preferenceViewModel =
                new ViewModelProvider(this).get(PreferenceViewModel.class);

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.layout.fragment_preference, rootKey);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}