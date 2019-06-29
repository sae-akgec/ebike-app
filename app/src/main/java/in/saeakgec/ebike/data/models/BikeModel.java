package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class BikeModel {

    @SerializedName("id")                       //json key
    private long id;                             //java field

    @SerializedName("image")
    private String image;

    @SerializedName("token")
    private String token;

    @SerializedName("number")
    private String number;

    @SerializedName("gf")
    private boolean geofencing;

    @SerializedName("helmet")
    private boolean helmet;

    @SerializedName("speed")
    private boolean speedLimit;

    @SerializedName("speed_limit")
    private int maxSpeedLimit;

    @SerializedName("admin")
    private int admin;

    @SerializedName("gf_limit")
    private int geofencingLimit;

    @SerializedName("gf_lat")
    private double geofencingLatitude;

    @SerializedName("gf_lon")
    private double geofencingLongitude;

    public BikeModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isGeofencing() {
        return geofencing;
    }

    public void setGeofencing(boolean geofencing) {
        this.geofencing = geofencing;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean isSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(boolean speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getMaxSpeedLimit() {
        return maxSpeedLimit;
    }

    public void setMaxSpeedLimit(int maxSpeedLimit) {
        this.maxSpeedLimit = maxSpeedLimit;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getGeofencingLimit() {
        return geofencingLimit;
    }

    public void setGeofencingLimit(int geofencingLimit) {
        this.geofencingLimit = geofencingLimit;
    }

    public double getGeofencingLatitude() {
        return geofencingLatitude;
    }

    public void setGeofencingLatitude(double geofencingLatitude) {
        this.geofencingLatitude = geofencingLatitude;
    }

    public double getGeofencingLongitude() {
        return geofencingLongitude;
    }

    public void setGeofencingLongitude(double geofencingLongitude) {
        this.geofencingLongitude = geofencingLongitude;
    }
}
