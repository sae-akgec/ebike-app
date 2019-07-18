package in.saeakgec.ebike.data.network;

import java.util.List;

import in.saeakgec.ebike.data.models.CarModel;
import in.saeakgec.ebike.data.models.DriverHistoryModel;
import in.saeakgec.ebike.data.models.RegisterModel;
import in.saeakgec.ebike.data.models.SignInModel;
import in.saeakgec.ebike.data.models.TokenModel;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/auth/login/")
    Single<Response<TokenModel>> signIn(@Body SignInModel signInModel);

    @POST("/api/auth/register/")
    Single<Response<Object>> signUp(@Body RegisterModel registerModel);

    @GET("/api/users/me/")
    Single<Response<Object>> checkAuth();

    @GET("/api/users/cars")
    Single<Response<List<CarModel>>> getAllcars();

    @GET("/api/users/history")
    Single<Response<List<DriverHistoryModel>>> getAllhistory();


}