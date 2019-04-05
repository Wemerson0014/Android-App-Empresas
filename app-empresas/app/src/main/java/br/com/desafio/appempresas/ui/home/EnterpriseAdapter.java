package br.com.desafio.appempresas.ui.home;

import br.com.desafio.appempresas.R;

import com.bumptech.glide.Glide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.desafio.appempresas.network.model.Enterprise;

import java.util.List;

public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.MyViewHolder> {

    private List<Enterprise> enterprises;
    private OnClickListener onClickListener;

    EnterpriseAdapter(List<Enterprise> enterprises, OnClickListener onClickListener) {
        this.enterprises = enterprises;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemEnterprise = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enterprise, parent, false);
        return new MyViewHolder(itemEnterprise);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Enterprise enterprises = this.enterprises.get(position);
        holder.configView(enterprises, onClickListener);
    }

    @Override
    public int getItemCount() {
        return enterprises.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageEnterprise;
        TextView textEnterprise;
        TextView textType;
        TextView textLocation;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEnterprise = itemView.findViewById(R.id.imageEnterprise);
            textEnterprise = itemView.findViewById(R.id.textEnterprise);
            textType = itemView.findViewById(R.id.textType);
            textLocation = itemView.findViewById(R.id.textLocation);
        }

        private void configView(final Enterprise enterprise, final OnClickListener onClickListener) {
            Glide.with(itemView.getContext())
                    .load("http://empresas.ioasys.com.br" + enterprise.getPhoto())
                    .placeholder(R.drawable.img_e_1_lista)
                    .into(imageEnterprise);
            textEnterprise.setText(enterprise.getName());
            textType.setText(enterprise.getEnterpriseType().getEnterpriseTypeName());
            textLocation.setText(enterprise.getCountry());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClicked(enterprise);
                }
            });
        }
    }

    interface OnClickListener {
        void onItemClicked(Enterprise enterprise);
    }
}