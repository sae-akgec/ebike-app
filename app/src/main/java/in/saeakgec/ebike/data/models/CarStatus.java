package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class CarStatus {

    private boolean gf;

    @SerializedName("gf_limit")
    private String gfLimit;

    @SerializedName("gf_lat")
    private String gfLat;

    @SerializedName("gf_lon")
    private String gfLon;

    @SerializedName("current_lat")
    private String currentLat;

    @SerializedName("current_lon")
    private String currentLon;

    private boolean status;

    @SerializedName("is_danger")
    private boolean isDanger;

    @SerializedName("driver_id")
    private String driverId;

    public CarStatus() {
    }

    public boolean isGf() {
        return gf;
    }

    public void setGf(boolean gf) {
        this.gf = gf;
    }

    public String getGfLimit() {
        return gfLimit;
    }

    public void setGfLimit(String gfLimit) {
        this.gfLimit = gfLimit;
    }

    public String getGfLat() {
        return gfLat;
    }

    public void setGfLat(String gfLat) {
        this.gfLat = gfLat;
    }

    public String getGfLon() {
        return gfLon;
    }

    public void setGfLon(String gfLon) {
        this.gfLon = gfLon;
    }

    public String getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(String currentLat) {
        this.currentLat = currentLat;
    }

    public String getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(String currentLon) {
        this.currentLon = currentLon;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDanger() {
        return isDanger;
    }

    public void setDanger(boolean danger) {
        isDanger = danger;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
