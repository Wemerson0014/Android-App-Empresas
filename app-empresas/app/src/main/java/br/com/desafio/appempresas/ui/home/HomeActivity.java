package br.com.desafio.appempresas.ui.home;

import br.com.desafio.appempresas.R;

import br.com.desafio.appempresas.network.RetrofitConfig;
import br.com.desafio.appempresas.network.model.Enterprise;
import br.com.desafio.appempresas.network.model.EnterprisesResponse;
import br.com.desafio.appempresas.network.model.Header;
import br.com.desafio.appempresas.ui.details.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import static br.com.desafio.appempresas.ui.details.DetailsActivity.EXTRA_ENTERPRISES;

public class HomeActivity extends AppCompatActivity {

    public final static String EXTRA_HEADER = "header";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Header header = getIntent().getParcelableExtra(EXTRA_HEADER);

        loadEnterprise(header);
    }

    private void configRecyclerView(final List<Enterprise> enterprises) {
        final RecyclerView recyclerView = findViewById(R.id.recyclerViewEnterprises);

        EnterpriseAdapter enterpriseAdapter = new EnterpriseAdapter(enterprises, new EnterpriseAdapter.OnClickListener() {
            @Override
            public void onItemClicked(Enterprise enterprise) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(EXTRA_ENTERPRISES, enterprise);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(enterpriseAdapter);
    }

    public void loadEnterprise(Header header) {
        Call<EnterprisesResponse> call = new RetrofitConfig().service().getEnterprises(header.getUid(), header.getClient(), header.getAccessToken());
        call.enqueue(new Callback<EnterprisesResponse>() {
            @Override
            public void onResponse(Call<EnterprisesResponse> call, Response<EnterprisesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        configRecyclerView(response.body().getEnterprises());
                    }
                }
            }

            @Override
            public void onFailure(Call<EnterprisesResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Erro inesperado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}