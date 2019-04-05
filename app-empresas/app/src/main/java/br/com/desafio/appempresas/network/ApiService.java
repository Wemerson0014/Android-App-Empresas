package br.com.desafio.appempresas.network;
import br.com.desafio.appempresas.network.model.EnterprisesResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("users/auth/sign_in")
    Call<Void> login(@Field("email") String email, @Field("password") String password);

    @GET("enterprises")
    Call<EnterprisesResponse> getEnterprises(@Header("uid")String uid,
                                             @Header("client")String client,
                                             @Header("access-token")String accessToken);
}