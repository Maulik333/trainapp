package shoping.softices.com.trainapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText FirstnameeditText;
    private EditText lastnameedittext;
    private EditText emaileditText;
    private EditText passwordeditText;
    private EditText confirmpasswordeditText;
    private Databasehelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btnsignUp = findViewById(R.id.btn_signup);
        FirstnameeditText = findViewById(R.id.edt_Enter_your_First_name);
        lastnameedittext =  findViewById(R.id.edt_Enter_your_last_name);
        emaileditText =  findViewById(R.id.edt_Enter_Email_Id);
        passwordeditText = findViewById(R.id.edt_Enter_your_Password);
        confirmpasswordeditText = findViewById(R.id.edt_Enter_Confirm_Password);
        databasehelper = new Databasehelper(this);
        FirstnameeditText.setText("aaa");
        lastnameedittext.setText("aaa");
        emaileditText.setText("aaa@aaa.aaa");
        passwordeditText.setText("aaaaaaaa");
        confirmpasswordeditText.setText("aaaaaaaa");
        TextView signin = findViewById(R.id.txt_Sign_in);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SigninActivity.class);
                startActivity(intent);
            }

        });


        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Firstname = FirstnameeditText.getText().toString();
                if (!isValidFirstname(Firstname)){
                    FirstnameeditText.setError("Invalid Firstname");
                }
                final String lastname = lastnameedittext.getText().toString();
                if (!isValidlastname(lastname)){
                    lastnameedittext.setError("Invalid lastname");
                }
                final String email = emaileditText.getText().toString();
                if (!isValidEmail(email)) {
                    emaileditText.setError("Invalid email");
                }
                final String Password = passwordeditText.getText().toString();
                if (!isValidPassword(Password)) {
                    passwordeditText.setError("Invalid Password");
                }
                final String confirmpassword = confirmpasswordeditText.getText().toString();
                if (confirmpassword==Password){
                    confirmpasswordeditText.setError("Invalid confirmpassword");
                }else {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(Password);
                    user.setFirst_name(Firstname);
                    user.setLast_name(lastname);
                    user.setGender("");
                    boolean isUserCreated = databasehelper.addUser(user);
                    if (databasehelper.checkUser(email)){
                        Intent intent = new Intent(view.getContext(), NavigationDrawerActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignUpActivity.this, "invalid id password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        TextView txtsignin = findViewById(R.id.txt_Sign_in);
        txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidFirstname(String FirstnameeditText) {
       String FIRST_NAME = "[_A-Za-z]+([A-Za-z]{1,})";
        Pattern pattern = Pattern.compile(FIRST_NAME);
        Matcher matcher = pattern.matcher(FIRST_NAME );
        return matcher.matches();
    }
    private boolean isValidlastname(String lastnameeditText) {
        String LAST_NAME =  "[_A-Za-z]+([A-Za-z]{1,})";
        Pattern pattern = Pattern.compile(LAST_NAME);
        Matcher matcher = pattern.matcher(LAST_NAME);
        return matcher.matches();
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String Password) {
        if (Password != null && Password.length() >= 6) {
            return true;
        }
        return false;
    }
    private boolean isValidconfirmpassword(String confirmpassword) {
        if (confirmpassword!= null && confirmpassword.length()== 6) {
            return true;
        }
        return false;
    }
}