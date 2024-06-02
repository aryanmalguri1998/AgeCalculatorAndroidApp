package com.example.agecalculator;

//importing all the required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText dateOfBirthEditText;
    private Button calculateAgeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize all the UI components
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        dateOfBirthEditText = findViewById(R.id.dateOfBirthEditText);
        calculateAgeButton = findViewById(R.id.calculateAgeButton);

        // setting click listener for the calculate button
        calculateAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the input values
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String dateOfBirth = dateOfBirthEditText.getText().toString().trim();

                // validating the input fields
                if (firstName.isEmpty() || lastName.isEmpty() || dateOfBirth.isEmpty()) {
                    showToast("Please fill in all fields");
                    return;
                }

                // Parsing date of birth and calculating age
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date birthDate = dateFormat.parse(dateOfBirth);
                    Calendar birthCal = Calendar.getInstance();
                    birthCal.setTime(birthDate);

                    Calendar today = Calendar.getInstance();
                    int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

                    if (today.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
                        age--;
                    }

                    // display age in a Toast
                    showToast("Your age is: " + age);

                } catch (ParseException e) {
                    // handle invalid date format
                    showToast("Invalid date format. Please use yyyy-MM-dd.");
                }
            }
        });
    }

    // method to show Toast in the center with larger text
    private void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();

        // I've used a custom toast here
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 100);  // 100 pixel offset from the top
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
