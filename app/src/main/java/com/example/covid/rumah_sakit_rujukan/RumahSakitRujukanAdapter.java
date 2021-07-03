package com.example.covid.rumah_sakit_rujukan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.network.response_faskes.DataItem;

import java.util.ArrayList;
import java.util.List;

public class RumahSakitRujukanAdapter extends RecyclerView.Adapter<RumahSakitRujukanAdapter.ViewHolder> {

    private ArrayList<DataItem> rumahSakitList = new ArrayList<>();
    private Context context;
    public RumahSakitRujukanAdapter (Context context){
        this.context = context;
    };

    public void setData(List<DataItem> list) {
//        rumahSakitList.clear();
        rumahSakitList.addAll(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView getAlamatRS() {
//            return alamatRS;
//        }
//
//        public TextView getNamaRS() {
//            return namaRS;
//        }
//
//        public Button getMapsRS() {
//            return mapsRS;
//        }

        private final TextView alamatRS,namaRS;
        private final Button mapsRS;

        public ViewHolder(View view) {
            super(view);
            alamatRS = (TextView) view.findViewById(R.id.tv_detailAlamat);
            namaRS = (TextView) view.findViewById(R.id.tv_namaRS);
            mapsRS = (Button) view.findViewById(R.id.btn_maps);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_rs_rujukan, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.namaRS.setText(String.valueOf(rumahSakitList.get(position).getNama()));
        viewHolder.alamatRS.setText(String.valueOf(rumahSakitList.get(position).getAlamat()));

        viewHolder.mapsRS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = String.format("geo: 0, 0?q= %s", rumahSakitList.get(position).getNama());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahSakitList.size();
    }
}
