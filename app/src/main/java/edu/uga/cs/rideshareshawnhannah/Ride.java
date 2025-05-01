package edu.uga.cs.rideshareshawnhannah;

public class Ride {
    private String origin;
    private String destination;
    private String date;
    private String driverUid;
    private String riderUid;
    private String id; // Firebase key if needed

    // Required for Firebase
    public Ride() {
    }

    public Ride(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public Ride(String origin, String destination, String date, String driverUid, String riderUid) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.driverUid = driverUid;
        this.riderUid = riderUid;
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

    public String getDriverUid() {
        return driverUid;
    }

    public String getRiderUid() {
        return riderUid;
    }

    public String getId() {
        return id;
    }

    public void setDriverUid(String driverUid) {
        this.driverUid = driverUid;
    }

    public void setRiderUid(String riderUid) {
        this.riderUid = riderUid;
    }

    public void setId(String id) {
        this.id = id;
    }
}
