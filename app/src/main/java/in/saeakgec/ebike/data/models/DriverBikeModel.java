package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class DriverBikeModel {

    @SerializedName("id")
    private long id;

    @SerializedName("user")
    private int user;

    @SerializedName("bike")
    private BikeModel bike;

    public DriverBikeModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public BikeModel getBike() {
        return bike;
    }

    public void setBike(BikeModel bike) {
        this.bike = bike;
    }
}
