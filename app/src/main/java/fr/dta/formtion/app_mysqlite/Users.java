package fr.dta.formtion.app_mysqlite;

import android.content.ContentValues;

/**
 * Created by thomas on 16/10/2017.
 */

public class Users {


    int id;
    String firstName;
    String lastName;
    String job;
    int age;

    public Users(int id, String firstName, String lastName, String job, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.age = age;
    }

    public Users( String firstName, String lastName, String job, int age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


