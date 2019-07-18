package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class TokenModel {

    @SerializedName("token")
    private String token;

    @SerializedName("success")
    private String success;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
