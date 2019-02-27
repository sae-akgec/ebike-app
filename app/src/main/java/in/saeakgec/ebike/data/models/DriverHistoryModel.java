package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class DriverHistoryModel {
    @SerializedName("id")
    private long id;

    @SerializedName("ride_coordinates")
    private String rideCoordinates;

    @SerializedName("avg_speed")
    private int avgSpeed;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("helmet")
    private boolean helmet;

    @SerializedName("bike")
    private BikeModel bike;

    public DriverHistoryModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRideCoordinates() {
        return rideCoordinates;
    }

    public void setRideCoordinates(String rideCoordinates) {
        this.rideCoordinates = rideCoordinates;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public BikeModel getBike() {
        return bike;
    }

    public void setBike(BikeModel bike) {
        this.bike = bike;
    }
}
