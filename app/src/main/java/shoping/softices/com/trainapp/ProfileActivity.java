package shoping.softices.com.trainapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {
    private EditText firstnameEditText, lastnameEditText, AddressEditText, MobileNumberEditText, EmailaddressEditText, DoBEditText;
    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";
    private Pattern pattern;
    private Matcher matcher;
    private Databasehelper databasehelper;

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firstnameEditText = findViewById(R.id.edt_profileFirstName);
        lastnameEditText = findViewById(R.id.edt_profileLastName);
        AddressEditText = findViewById(R.id.edt_profileAddress);
        MobileNumberEditText = findViewById(R.id.edt_profileMobileNo);
        EmailaddressEditText = findViewById(R.id.edt_profileEmailAddress);
        databasehelper = new Databasehelper(this);
        Button btnupdate = findViewById(R.id.btn_Update);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstname = firstnameEditText.getText().toString();
                final String lastname = lastnameEditText.getText().toString();
                final String address = AddressEditText.getText().toString();
                final String mobilenumber = MobileNumberEditText.getText().toString();
                if (!isValidfristname(firstname)) {
                    firstnameEditText.setError("Invalid fristanme");
                } else if (!isValidlastname(lastname)) {
                    lastnameEditText.setError("Invalid lastanme");
                }  else if (!isValidAddress(address)) {
                    AddressEditText.setError("Invalid Address");
                } else if (!isvalidnumber(mobilenumber)) {
                    MobileNumberEditText.setError("Invalid number");
                } else {
                   SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(ProfileActivity.this);
                    String email = sharedPreferences.getString("email", "");
                    User user = new User();
                    user.setFirst_name(firstname);
                    user.setLast_name(lastname);
                    user.setEmail(email);
                    user.setAddress(address);
                    user.setMobile_number(mobilenumber);

                    boolean isupdateUser = databasehelper.updateUser(user);
                    if (isupdateUser) {
                        Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(ProfileActivity.this, "invalid id ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidlastname(String lastname) {
        return lastname.length() > 0;
    }

    private boolean isValidfristname(String fristname) {
        return fristname.length() > 0;
    }

    private boolean isValidAddress(String address) {
        return address.length() > 0;
    }

    private boolean isvalidnumber(String MoblieNumber) {
        return MoblieNumber.length() > 0;
    }
}
