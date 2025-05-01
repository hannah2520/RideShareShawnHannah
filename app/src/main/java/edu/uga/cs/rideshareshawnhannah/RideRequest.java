package edu.uga.cs.rideshareshawnhannah;

public class RideRequest {
    private String origin;
    private String destination;
    private String date;
    private String notes;

    private String driverUid;
    private String riderUid;
    private String id;

    public RideRequest() {
        // required for Firebase
    }

    public RideRequest(String origin, String destination, String date, String notes) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.notes = notes;
    }

    public RideRequest(String origin, String destination, String date, String notes,
                       String driverUid, String riderUid, String id) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.notes = notes;
        this.driverUid = driverUid;
        this.riderUid = riderUid;
        this.id = id;
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
