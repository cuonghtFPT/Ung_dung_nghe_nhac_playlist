package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;
public class Fragment_Cai_Dat extends Fragment {
    Spinner spinner;
    ArrayAdapter<String> adapter;
    public boolean isSpinnerInitialized = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cai_d_a_t, container, false);
        String[] list = new String[]{"",getString(R.string.english),getString(R.string.vietnamese)};
        spinner = view.findViewById(R.id.language);
        adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isSpinnerInitialized) {
                    String selectedLanguage = (String) parent.getItemAtPosition(position);
                    if (selectedLanguage.equals("Tiếng Việt") || selectedLanguage.equals("Vietnamese") ) {
                        setLocale("vi");
                    } else if (selectedLanguage.equals("Tiếng Anh") || selectedLanguage.equals("English")) {
                        setLocale("en");
                    }
                    restartActivity();
                } else {
                    isSpinnerInitialized = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view ;

    }
    private void setLocale(String languageCode) {
        SharedPreferences preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        preferences.edit().putString("language", languageCode).apply();

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void restartActivity() {
        Intent intent = requireActivity().getIntent();
        requireActivity().finish();
        startActivity(intent);
    }
}