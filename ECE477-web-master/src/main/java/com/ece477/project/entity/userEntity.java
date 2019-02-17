package com.ece477.project.entity;
import java.io.Serializable;

public class userEntity implements Serializable{
    //each variable name corresponding to one column in database
    private int UserID;
    private String LastName;
    private String FirstName;
    private String Email;
    private int BoxID;
    //auto generated getter and setter

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getBoxID() {
        return BoxID;
    }

    public void setBoxID(int boxID) {
        BoxID = boxID;
    }
}
