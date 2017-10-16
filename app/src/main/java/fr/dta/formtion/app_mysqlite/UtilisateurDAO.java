package fr.dta.formtion.app_mysqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 16/10/2017.
 */

public class UtilisateurDAO  {

    UserDataSource usrDataSource;

    final String TABLE_NAME = "User";
    final String COL_FIRSTNAME= "COL_FIRSTNAME";
    final String COL_LASTNAME= "COL_LASTNAME";
    final String COL_AGE= "COL_AGE";
    final String COL_JOB= "COL_JOB";
    final String COL_ID = "COL_ID";

    public UtilisateurDAO (UserDataSource userDataSource){
        this.usrDataSource = userDataSource;
    }


    public synchronized Users create(Users POJO) {
        ContentValues values = new ContentValues();
        values.put(COL_FIRSTNAME, POJO.getFirstName());
        values.put(COL_LASTNAME, POJO.getLastName());
        values.put(COL_AGE, POJO.getAge());
        values.put(COL_JOB,POJO.getJob());

        int id = (int) this.usrDataSource.getDb().insert(TABLE_NAME, null, values);
        POJO.setId(id);


        return POJO;
    }

    public synchronized Users update(Users POJO) {
        ContentValues values = new ContentValues();
        values.put(COL_FIRSTNAME, POJO.getFirstName());
        values.put(COL_LASTNAME, POJO.getLastName());
        values.put(COL_AGE, POJO.getAge());
        values.put(COL_JOB,POJO.getJob());

        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(POJO.getId())};

        this.usrDataSource.getDb().update(TABLE_NAME, values, clause, clauseArgs);
        // return the POJO
        return POJO;
    }

    public synchronized void delete(Users POJO) {
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(POJO.getId())};
        this.usrDataSource.getDb().delete(TABLE_NAME, clause, clauseArgs);
    }

    public Users read(Users POJO) {
        String[] allColumns = new String[]{COL_FIRSTNAME, COL_LASTNAME, COL_AGE, COL_JOB};
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(POJO.getId())};

        Cursor cursor = this.usrDataSource.getDb().query(TABLE_NAME, allColumns, "ID = ?", clauseArgs, null, null, null);
        cursor.moveToFirst();
        POJO.setFirstName(cursor.getString(1));
        POJO.setLastName(cursor.getString(2));
        POJO.setAge(cursor.getInt(3));
        POJO.setJob(cursor.getString(4));
        cursor.close();
        return POJO;
    }

    public List<Users> readAll() {

        String[] allColumns = new String[]{COL_ID, COL_FIRSTNAME, COL_LASTNAME, COL_JOB,COL_AGE};

        Cursor cursor = usrDataSource.getDb().query(TABLE_NAME, allColumns, null, null, null, null, null);
        List<Users> users = new ArrayList<Users>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(new Users(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }



}


