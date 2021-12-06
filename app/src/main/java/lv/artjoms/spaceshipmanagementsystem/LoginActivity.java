package lv.artjoms.spaceshipmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit1 = findViewById(R.id.username);
        edit2 = findViewById(R.id.password);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LOGIN
                login();
            }
        });
    }

    public void login() {
        String username = edit1.getText().toString();
        String password = edit2.getText().toString();

        // Validation of the username and password
        if( username.equals("") || password.equals("")) {
            Toast.makeText(this, "Username or password are empty", Toast.LENGTH_LONG).show();
        } else if ( username.equals("Artjoms") && password.equals("qwerty")) {
            Toast.makeText(this, "login was succesful", Toast.LENGTH_LONG).show();
            Intent welcome = new Intent(this, WelcomeActivity.class);
            startActivity(welcome);
        } else {
            Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_LONG).show();
        }
    }
}