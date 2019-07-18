package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class ShareCarModel {
    @SerializedName("car_id")
    private String carId;

    @SerializedName("email")
    private String email;

    public ShareCarModel() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
