package edu.uga.cs.rideshareshawnhannah;

public class Ride {
    private String origin;
    private String destination;
    private String date;

    // Needed for Firebase
    public Ride() {}

    public Ride(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }
}
