package com.example.covid.kasus_harian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.network.response.ContentItem;

import java.util.ArrayList;
import java.util.List;

public class KasusHarianAdapter extends RecyclerView.Adapter<KasusHarianAdapter.ViewHolder> {

    private ArrayList<ContentItem> kasusHarianList = new ArrayList<>();

    public void setData(List<ContentItem> data){
        kasusHarianList.clear();
        kasusHarianList.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView sembuh, meninggal, terkonfirmasi, tanggal;

        public TextView getSembuh() {
            return sembuh;
        }

        public TextView getMeninggal() {
            return meninggal;
        }

        public TextView getTerkonfirmasi() {
            return terkonfirmasi;
        }

        public TextView getTanggal() {
            return tanggal;
        }

        public ViewHolder(View view) {
            super(view);
            sembuh = (TextView) view.findViewById(R.id.tv_sembuh);
            meninggal = (TextView) view.findViewById(R.id.tv_meninggal);
            terkonfirmasi = (TextView) view.findViewById(R.id.tv_terkonfirmasi);
            tanggal = (TextView) view.findViewById(R.id.tv_tanggal);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_kasus_covid, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getSembuh().setText(String.valueOf(kasusHarianList.get(position).getConfirmationSelesai()));
        viewHolder.getMeninggal().setText(String.valueOf(kasusHarianList.get(position).getConfirmationMeninggal()));
        viewHolder.getTerkonfirmasi().setText(String.valueOf(kasusHarianList.get(position).getConfirmationDiisolasi()));
        viewHolder.getTanggal().setText(kasusHarianList.get(position).getTanggal());
    }
    @Override
    public int getItemCount() {
        return kasusHarianList.size();
    }
}
