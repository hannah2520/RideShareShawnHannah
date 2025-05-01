package edu.uga.cs.rideshareshawnhannah;

public class User {
    private String uid;
    private String email;
    private int points;

    public User() {
        // required for Firebase
    }

    public User(String uid, String email, int points) {
        this.uid = uid;
        this.email = email;
        this.points = points;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
