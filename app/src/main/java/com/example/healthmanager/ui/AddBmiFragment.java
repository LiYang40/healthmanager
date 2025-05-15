package com.example.healthmanager.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthmanager.R;
import com.example.healthmanager.data.HealthManagerDatabase;
import com.example.healthmanager.model.BMIRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddBmiFragment extends Fragment {

    private EditText weightInput;
    private EditText heightInput;
    private TextView bmiResult;
    private TextView bmiCategory;
    private TextView selectedDate;
    private Button saveButton;
    private Button datePickerButton;

    private float weight = 0f;
    private float height = 0f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_bmi, container, false);

        // Initialize views
        weightInput = view.findViewById(R.id.weight_input);
        heightInput = view.findViewById(R.id.height_input);
        bmiResult = view.findViewById(R.id.bmi_result);
        bmiCategory = view.findViewById(R.id.bmi_category);
        selectedDate = view.findViewById(R.id.selected_date);
        saveButton = view.findViewById(R.id.save_button);
        datePickerButton = view.findViewById(R.id.date_picker_button);

        // Add TextWatchers for dynamic BMI calculation
        weightInput.addTextChangedListener(bmiTextWatcher);
        heightInput.addTextChangedListener(bmiTextWatcher);

        // Set up date picker
        datePickerButton.setOnClickListener(v -> openDatePicker());

        // Save button logic
        saveButton.setOnClickListener(v -> saveRecord());

        return view;
    }

    private final TextWatcher bmiTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateBmi();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void calculateBmi() {
        String weightText = weightInput.getText().toString();
        String heightText = heightInput.getText().toString();

        if (!weightText.isEmpty() && !heightText.isEmpty()) {
            weight = Float.parseFloat(weightText);
            height = Float.parseFloat(heightText);

            if (height > 0) {
                float bmi = weight / (height * height);
                bmiResult.setText(String.format(Locale.getDefault(), "BMI: %.2f", bmi));

                // Determine BMI category
                String category;
                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi < 25) {
                    category = "Normal weight";
                } else if (bmi < 30) {
                    category = "Overweight";
                } else {
                    category = "Obesity";
                }
                bmiCategory.setText("BMI Category: " + category);
            }
        }
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                    selectedDate.setText(sdf.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show();
    }

    private void saveRecord() {
        String date = selectedDate.getText().toString();
        if (weight > 0 && height > 0 && !date.isEmpty()) {
            float bmi = weight / (height * height);

            BMIRecord record = new BMIRecord(weight, height, bmi, date);

            // Save to database
            Executors.newSingleThreadExecutor().execute(() -> {
                HealthManagerDatabase.getInstance(getContext()).bmiDao().insert(record);
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "BMI record saved!", Toast.LENGTH_SHORT).show();
                });
            });
        } else {
            Toast.makeText(getContext(), "Please complete all fields.", Toast.LENGTH_SHORT).show();
        }
    }
}