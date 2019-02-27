package in.saeakgec.ebike.data.network;

import in.saeakgec.ebike.data.models.DriverBikeModel;
import in.saeakgec.ebike.data.models.DriverHistoryModel;
import in.saeakgec.ebike.data.models.ListModel;
import in.saeakgec.ebike.data.models.RegisterModel;
import in.saeakgec.ebike.data.models.SignInModel;
import in.saeakgec.ebike.data.models.TokenModel;
import io.reactivex.Completable;
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

    @GET("/api/user/profile/")
    Single<Response<Object>> checkAuth();

    @GET("/api/user/bikes")
    Single<Response<ListModel<DriverBikeModel>>> getAllbikes();

    @GET("/api/user/history")
    Single<Response<ListModel<DriverHistoryModel>>> getAllhistory();

}