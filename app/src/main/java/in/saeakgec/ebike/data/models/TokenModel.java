package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class TokenModel {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
