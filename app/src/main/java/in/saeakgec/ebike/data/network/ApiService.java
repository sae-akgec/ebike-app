package in.saeakgec.ebike.data.network;

import in.saeakgec.ebike.data.models.DriverCarModel;
import in.saeakgec.ebike.data.models.DriverHistoryModel;
import in.saeakgec.ebike.data.models.ListModel;
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

    @GET("/api/user/me/")
    Single<Response<Object>> checkAuth();

    @GET("/api/user/cars")
    Single<Response<ListModel<DriverCarModel>>> getAllcars();

    @GET("/api/user/history")
    Single<Response<ListModel<DriverHistoryModel>>> getAllhistory();


}