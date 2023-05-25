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
import mrandroid.app.model.CartModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MedicineViewHolder> {
    private List<CartModel> list = new ArrayList<>();
    private OnItemClickListener listener;
    private boolean canDelete = false;
    private double total = 0.0;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        CartModel item = list.get(holder.getAdapterPosition());

        holder.ivImg.setImageResource(item.getPlantImage());
        holder.tvPlantName.setText(item.getPlantName());
        holder.tvColor.setText("Color: "+item.getColor());
        holder.tvNumber.setText("Qty: "+item.getQty());
        holder.tvDescription.setText(item.getDescription());
        holder.ratingBar.setRating(item.getRate());

        if (canDelete) holder.ivDelete.setVisibility(View.VISIBLE);
        else holder.ivDelete.setVisibility(View.GONE);

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

    public void setList(List<CartModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        this.notifyDataSetChanged();
    }

    public double getTotal() {
        total = 0.0;
        for (CartModel item : list) {
            total += item.getPrice() * item.getQty();
        }
        return total;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivImg;
        private final ImageView ivDelete;
        private final TextView tvPlantName;
        private final TextView tvColor;
        private final TextView tvNumber;
        private final TextView tvDescription;
        private final RatingBar ratingBar;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImg = itemView.findViewById(R.id.ivImg);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            tvPlantName = itemView.findViewById(R.id.tvPlantName);
            tvColor = itemView.findViewById(R.id.tvColor);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(CartModel plantModel);
        void onItemDelete(CartModel plantModel);
    }
}