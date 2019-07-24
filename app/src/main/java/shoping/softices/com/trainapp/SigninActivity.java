package shoping.softices.com.trainapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {
    private EditText emaileditText;
    private EditText passwordeditText;
    private Databasehelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button btnsignin = findViewById(R.id.btn_signin);
        emaileditText = findViewById(R.id.edt_Enter_Email_Id);
        passwordeditText =findViewById(R.id.edt_Enter_your_Password);
        databasehelper = new Databasehelper(this);
        emaileditText.setText("aaa@aaa.aaa");
        passwordeditText.setText("aaaaaaaa");
        btnsignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final String email = emaileditText.getText().toString();
                if (!isValidEmail(email)) {
                    emaileditText.setError("Invalid email");
                }
                final String pass = passwordeditText.getText().toString();
                if (!isValidPassword(pass)) {
                    passwordeditText.setError("Invalid Password");
                }else {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(pass);
                    if(databasehelper.checkUser(email,pass)){
                        boolean checkUser = databasehelper.addUser(user);
                        isUserLoggedIn(email,true);
                        Intent intent = new Intent(SigninActivity.this, NavigationDrawerActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SigninActivity.this, "invalid id password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView txtforget = findViewById(R.id.txt_forgetpassword);
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ForgetActivity.class);
                startActivityForResult(intent,0);
            }
        });
        TextView txtcreateaccount = findViewById(R.id.txt_Create_a_new_Account);
        txtcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SignUpActivity.class);
                startActivityForResult(intent,0);

            }
        });
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private void isUserLoggedIn(String email, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putBoolean("is_user_login", value);
        editor.apply();
    }

}
