package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarModel {

    @SerializedName("_id")
    private String id;

    @SerializedName("image")
    private String image;

    @SerializedName("car_no")
    private String carNumber;

    @SerializedName("speed")
    private String speed;

    @SerializedName("speed_limit")
    private String speedLimit;

    @SerializedName("danger_email")
    private String dangerEmail;

    @SerializedName("car_status")
    private CarStatus carStatus;

    @SerializedName("drivers")
    private List<CarDriver> drivers;

    public CarModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(String speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getDangerEmail() {
        return dangerEmail;
    }

    public void setDangerEmail(String dangerEmail) {
        this.dangerEmail = dangerEmail;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public List<CarDriver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<CarDriver> drivers) {
        this.drivers = drivers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
