package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {

    Context context = this;
    UserDataSource source ;
    UtilisateurDAO utilisateur ;

    EditText firstNameET;
    EditText lastNameET;
    EditText jobET;
    EditText ageET;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        firstNameET = (EditText) findViewById(R.id.firstName);
        lastNameET = (EditText) findViewById(R.id.lastName);
        jobET = (EditText) findViewById(R.id.jobInput);
        ageET = (EditText) findViewById(R.id.ageInput);

        button = (Button) findViewById(R.id.addUser);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTheUser();
            }
        });



        source = new UserDataSource(context);
        source.open();
        utilisateur = source.newUtilisateurDAO();

    }

    private void addTheUser() {
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String job = jobET.getText().toString();
        int age = Integer.valueOf(ageET.getText().toString());

        Users newUser = new Users(firstName, lastName, job, age);

        utilisateur.create(newUser);

    }

}
