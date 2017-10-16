package fr.dta.formtion.app_mysqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by thomas on 16/10/2017.
 * With almost no coffee...
 */

public class ModifyUserActivity extends AppCompatActivity {

    Context context = this;
    UserDataSource source ;
    UtilisateurDAO utilisateur ;

    EditText firstNameET;
    EditText lastNameET;
    EditText jobET;
    EditText ageET;
    Button button;
    Button goBack;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Assigning all the components
        assignComponents();

        //Creation of the buttons and the listeners
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyUserActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //Opening the Database
        source = new UserDataSource(context);
        source.open();
        utilisateur = source.newUtilisateurDAO();

    }

    private void assignComponents(){
        // Catching the intent form the itemView
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String job = intent.getStringExtra("job");
        String age = intent.getStringExtra("age");
        userId = intent.getIntExtra("id",-1);

        firstNameET = (EditText) findViewById(R.id.firstName);
        lastNameET = (EditText) findViewById(R.id.lastName);
        jobET = (EditText) findViewById(R.id.jobInput);
        ageET = (EditText) findViewById(R.id.ageInput);

        firstNameET.setText(firstName);
        lastNameET.setText(lastName);
        ageET.setText(age);
        jobET.setText(job);


        button = (Button) findViewById(R.id.addUser);
        goBack = (Button) findViewById(R.id.buttonReturn);
        button.setText("Modify User");

    }


    //Gathering all the modifications and sending them to update the database
    private void updateUser() {

        String firstNameChange = firstNameET.getText().toString();
        String lastNameChange = lastNameET.getText().toString();
        String jobChange = jobET.getText().toString();

        //Verification if the field age is filled or else the app will crash
        int ageChange = -1;
        if(!ageET.getText().toString().equals("")){
            ageChange = Integer.valueOf(ageET.getText().toString());
        }

        //Check if all the inputs are filled
        if(firstNameChange.equals("") || lastNameChange.equals("") || jobChange.equals("") || ageChange<1){
            Toast.makeText(this, "Veuillez remplir tous les champs ",Toast.LENGTH_LONG).show();
        }else{
            //Creation of the new version of the user - the id is important
            Users newUser = new Users(userId,firstNameChange, lastNameChange, jobChange, ageChange);

            //Updating the new user
            utilisateur.update(newUser);

            //There id no verification that the user has really been updated so this Toast is just to notify the user
            Toast.makeText(this, "The User has been updated", Toast.LENGTH_SHORT).show();
        }
    }


}
