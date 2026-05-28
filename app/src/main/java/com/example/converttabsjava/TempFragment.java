package com.example.converttabsjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TempFragment extends Fragment {

    private RadioGroup rgTemp;
    private RadioButton rbCtoF;
    private RadioButton rbFtoC;
    private EditText etTempInput;
    private Button btnConvertTemp;
    private TextView tvTempResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);

        rgTemp = view.findViewById(R.id.rgTemp);
        rbCtoF = view.findViewById(R.id.rbCtoF);
        rbFtoC = view.findViewById(R.id.rbFtoC);
        etTempInput = view.findViewById(R.id.etTempInput);
        btnConvertTemp = view.findViewById(R.id.btnConvertTemp);
        tvTempResult = view.findViewById(R.id.tvTempResult);

        btnConvertTemp.setOnClickListener(v -> convertTemperature());

        return view;
    }

    private void convertTemperature() {
        String input = etTempInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(getContext(), "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double value = Double.parseDouble(input);
            double result;
            String unit;

            if (rbCtoF.isChecked()) {
                result = (1.8 * value) + 32;
                unit = " °F";
            } else {
                result = (value - 32) / 1.8;
                unit = " °C";
            }

            tvTempResult.setText(String.format(Locale.getDefault(), "Résultat : %.2f%s", result, unit));
        } catch (NumberFormatException exception) {
            Toast.makeText(getContext(), "Valeur invalide", Toast.LENGTH_SHORT).show();
        }
    }
}
