package in.saeakgec.ebike.data.network;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    // Relay 1 & 2
    @GET("/api/user/profile/")
    Single<Response<Object>> checkAuth();

    @GET("/api/bikes")
    Single<Response<Object>> getAllbikes();

}