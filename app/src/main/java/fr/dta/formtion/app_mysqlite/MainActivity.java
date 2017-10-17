package fr.dta.formtion.app_mysqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/*
    NOTE ABOUT THE BUILD
    The list of users is created by the Adapter of the RecyclerView. In order to the most of the
    magic, you'll need to check that out.
    The database concepts are located in the UserDBHlper, UserDataSource and UtilisateurDAO classes.
 */

public class MainActivity extends AppCompatActivity  {
    Context context = this;
    UserDataSource source ;
    UtilisateurDAO utilisateur ;
    List<Users> userPool = new ArrayList<>();
    ImageButton button;
    Button buttonHide;
    LinearLayout hidingLayout;

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INIT DATABASE
        source = UserDataSource.getInstance(context); // Create the new db
        utilisateur = source.newUtilisateurDAO();     // Allows the user of the methods in UtilisateurDAO


        //Declaration of the button that redirect to the AddUser Activity
        button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        //Mecanism to initialize and hide the advises
        buttonHide=(Button) findViewById(R.id.buttonHide);
        hidingLayout = (LinearLayout) findViewById(R.id.hidingLayout);
        buttonHide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hidingLayout.setVisibility(View.GONE);
            }
        });



        /*
            LIST CREATION
         */

        userPool= utilisateur.readAll();

        //MECANISM for the recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(userPool,context);
        recyclerView.setAdapter(mAdapter);



    }
}
