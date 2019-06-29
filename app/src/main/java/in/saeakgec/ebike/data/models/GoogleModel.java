package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class GoogleModel {
    @SerializedName("username_or_email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
