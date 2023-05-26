package mrandroid.app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mrandroid.app.R;
import mrandroid.app.model.RateModel;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.MedicineViewHolder> {
    private List<RateModel> list = new ArrayList<>();
    private OnItemClickListener listener;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        RateModel item = list.get(holder.getAdapterPosition());

        holder.tvComment.setText(item.getComment());
        holder.ratingBar.setRating(item.getRate());

        holder.ivDelete.setOnClickListener(view -> {
            listener.onItemDelete(item);
        });

        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<RateModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivDelete;
        private final RatingBar ratingBar;
        private final TextView tvComment;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDelete = itemView.findViewById(R.id.ivDelete);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvComment = itemView.findViewById(R.id.tvComment);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(RateModel rateModel);
        void onItemDelete(RateModel rateModel);
    }
}