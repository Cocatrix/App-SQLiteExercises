package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    Context context = this;
    UserDataSource source ;
    UtilisateurDAO utilisateur ;
    List<Users> userPool = new ArrayList<>();
    ImageButton button;


    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.listView);
        source = new UserDataSource(context);
        source.open();
        utilisateur = source.newUtilisateurDAO();


        button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });


        /*
            LIST CREATION
         */

        userPool= utilisateur.readAll();

        Log.d("Lecture :" , " la pool est constituée de "+userPool.toString());

        //Array of Strings needed in order to get only the names for the adapter
        String[] namePool = new String[userPool.size()];
        for(int i = 0 ; i<userPool.size();i++){
            namePool[i]= userPool.get(i).getFirstName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, namePool);
        myListView.setAdapter(adapter);



    }
}
