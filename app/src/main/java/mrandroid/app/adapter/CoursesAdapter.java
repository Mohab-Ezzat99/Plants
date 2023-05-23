package mrandroid.app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import mrandroid.app.R;
import mrandroid.app.model.CourseModel;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MedicineViewHolder> {
    private List<CourseModel> list = new ArrayList<>();
    private OnItemClickListener listener;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        CourseModel item = list.get(holder.getAdapterPosition());

        holder.tvCourseName.setText(item.getCourseName());
        holder.tvTeacherName.setText(item.getTeacherName());
        holder.tvDescription.setText(item.getDescription());
        holder.ratingBar.setRating(item.getRate());

        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CourseModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCourseName;
        private final TextView tvTeacherName;
        private final TextView tvDescription;
        private final RatingBar ratingBar;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvTeacherName = itemView.findViewById(R.id.tvTeacherName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(CourseModel courseModel);
    }
}