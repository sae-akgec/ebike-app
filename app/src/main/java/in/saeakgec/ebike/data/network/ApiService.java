package in.saeakgec.ebike.data.network;

import java.util.List;

import in.saeakgec.ebike.data.models.CarModel;
import in.saeakgec.ebike.data.models.DriverHistoryModel;
import in.saeakgec.ebike.data.models.RegisterModel;
import in.saeakgec.ebike.data.models.ShareCarModel;
import in.saeakgec.ebike.data.models.SignInModel;
import in.saeakgec.ebike.data.models.TokenModel;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/auth/login/")
    Single<Response<TokenModel>> signIn(@Body SignInModel signInModel);

    @POST("/api/auth/register/")
    Single<Response<Object>> signUp(@Body RegisterModel registerModel);

    @GET("/api/users/me/")
    Single<Response<Object>> checkAuth();

    @GET("/api/users/cars")
    Single<Response<List<CarModel>>> getAllcars();

    @GET("/api/cars/{id}")
    Single<Response<CarModel>> getCarById(@Path("id") String id);

    @GET("/api/users/history")
    Single<Response<List<DriverHistoryModel>>> getAllhistory();

    @GET("/api/users/on/{id}")
    Single<Response<Object>> turnOn(@Path("id") String id);

    @GET("/api/users/off/{id}")
    Single<Response<Object>> turnOff(@Path("id") String id);

    @POST("/api/cars/share/")
    Single<Response<Object>> share(@Body ShareCarModel shareCarModel);

    @GET("/api/history/car/{id}")
    Single<Response<List<DriverHistoryModel>>> getCarHistory(@Path("id") String carId);
}