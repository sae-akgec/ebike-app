package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class CarDriver {

    @SerializedName("driver_id")
    private String driverId;

    @SerializedName("is_admin")
    private boolean isAdmin;

    public CarDriver() {
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
