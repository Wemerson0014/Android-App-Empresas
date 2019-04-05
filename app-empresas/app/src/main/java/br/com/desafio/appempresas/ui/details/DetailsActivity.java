package br.com.desafio.appempresas.ui.details;
import br.com.desafio.appempresas.R;
import androidx.appcompat.app.AppCompatActivity;
import br.com.desafio.appempresas.network.model.Enterprise;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    public final static String EXTRA_ENTERPRISES = "extra_emterprises";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Enterprise enterprise = getIntent().getParcelableExtra(EXTRA_ENTERPRISES);

        configLayout(enterprise);
    }

    private void configLayout(Enterprise enterprise){
        ImageView imageDetail;
        TextView textInfo;

        imageDetail = findViewById(R.id.imageDetail);
        textInfo = findViewById(R.id.textInfo);


        Glide.with(this)
                .load("http://empresas.ioasys.com.br" + enterprise.getPhoto())
                .placeholder(R.drawable.img_e_1_lista)
                .into(imageDetail);
        textInfo.setText(enterprise.getDescription());
    }
}