package mrandroid.app.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mrandroid.app.R;

public class SelectImagesAdapter extends RecyclerView.Adapter<SelectImagesAdapter.MedicineViewHolder> {
    private List<Integer> list = new ArrayList<>();
    private OnItemClickListener listener;
    private int selectedImg = -1;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_selection, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Integer item = list.get(holder.getAdapterPosition());

        holder.ivImage.setImageResource(item);

        if (selectedImg == item) holder.cvImgRoot.setCardBackgroundColor(Color.RED);
        else holder.cvImgRoot.setCardBackgroundColor(Color.TRANSPARENT);

        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(item);
            selectedImg = item;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Integer> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivImage;
        private final CardView cvImgRoot;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImg);
            cvImgRoot = itemView.findViewById(R.id.cvImgRoot);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Integer item);
    }
}