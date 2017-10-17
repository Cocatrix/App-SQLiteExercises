package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    /*
        Activity allowing the user to add a user
     */

public class AddUserActivity extends AppCompatActivity {

    Context context = this;
    UserDataSource source ;
    UtilisateurDAO utilisateur ;

    EditText firstNameET;
    EditText lastNameET;
    EditText jobET;
    EditText ageET;
    Button button;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Assignation of the EditText and Buttons to their Id
        assignComponents();

        // 2 onCick Listener for the 2 buttons
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTheUser();
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


        //Opening of the database
        source = new UserDataSource(context);
        source.open();
        utilisateur = source.newUtilisateurDAO();

    }

    private void assignComponents(){
        //the findViewById
        firstNameET = (EditText) findViewById(R.id.firstName);
        lastNameET = (EditText) findViewById(R.id.lastName);
        jobET = (EditText) findViewById(R.id.jobInput);
        ageET = (EditText) findViewById(R.id.ageInput);

        button = (Button) findViewById(R.id.addUser);
        goBack = (Button) findViewById(R.id.buttonReturn);
    }


    private void addTheUser() {
        //gathering of the data
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String job = jobET.getText().toString();
        int age = -1;
        //Verification if the field age is filled or else the app will crash
        if(!ageET.getText().toString().equals("")){
            age = Integer.valueOf(ageET.getText().toString());
        }

        //Check if all the inputs are filled
        if(firstName.equals("") || lastName.equals("") || job.equals("") || age<1){
            Toast.makeText(this, "Veuillez remplir tous les champs ",Toast.LENGTH_LONG).show();
        }else {
            //Creation of a new user
            Users newUser = new Users(firstName, lastName, job, age);

            //New user added to the pool
            Users test = utilisateur.create(newUser);
            if (newUser.getId() != -1) {
                Toast.makeText(this, "The User has been added", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
