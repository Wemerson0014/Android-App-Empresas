package br.com.desafio.appempresas.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit= new Retrofit.Builder()
                .baseUrl("http://empresas.ioasys.com.br/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService service() {
        return this.retrofit.create(ApiService.class);
    }
}