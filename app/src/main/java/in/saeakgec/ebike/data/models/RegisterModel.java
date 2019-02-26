package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("password_2")
    private String confirmPassword;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

}
