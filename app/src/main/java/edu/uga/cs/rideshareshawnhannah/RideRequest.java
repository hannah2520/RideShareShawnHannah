package edu.uga.cs.rideshareshawnhannah;

public class RideRequest {
    private String origin;
    private String destination;
    private String date;
    private String notes;

    // Needed for Firebase
    public RideRequest() {}

    public RideRequest(String origin, String destination, String date, String notes) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.notes = notes;
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

    public String getNotes() {
        return notes;
    }
}
