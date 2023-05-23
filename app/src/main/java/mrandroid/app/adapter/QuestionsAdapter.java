package mrandroid.app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mrandroid.app.R;
import mrandroid.app.model.QuestionModel;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MedicineViewHolder> {
    private List<QuestionModel> list = new ArrayList<>();
    private OnItemClickListener listener;
    private boolean canEdit = false;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        QuestionModel item = list.get(holder.getAdapterPosition());

        holder.et_question.setEnabled(canEdit);
        holder.et_option1.setEnabled(canEdit);
        holder.et_option2.setEnabled(canEdit);

        if(canEdit) {
            item.setQuestion(Objects.requireNonNull(holder.et_question.getText()).toString());
            item.setOption1(Objects.requireNonNull(holder.et_option1.getText()).toString());
            item.setOption2(Objects.requireNonNull(holder.et_option2.getText()).toString());
        } else  {
            holder.et_question.setText(item.getQuestion());
            holder.et_option1.setText(item.getOption1());
            holder.et_option2.setText(item.getOption2());
        }

        holder.rgOptions.setOnCheckedChangeListener((radioGroup, i) -> {
            if (canEdit) {
                switch (i) {
                    case R.id.rbOption1:
                        item.setAnswer(1);
                        break;
                    case R.id.rbOption2:
                        item.setAnswer(2);
                        break;
                }
            } else {
                switch (i) {
                    case R.id.rbOption1:
                        item.setSelectedAnswer(1);
                        break;
                    case R.id.rbOption2:
                        item.setSelectedAnswer(2);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<QuestionModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<QuestionModel> getList() {
        return list;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
        this.notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public boolean isFieldsRequired() {
        for (QuestionModel questionModel : list) {
            if (questionModel.isFieldsEmpty()) return true;
        }
        return false;
    }

    public boolean isAnswerRequired() {
        for (QuestionModel questionModel : list) {
            if (questionModel.isAnswerRequired()) return true;
        }
        return false;
    }

    public String calculateScore() {
        int correctCounter = 0;
        for (QuestionModel questionModel : list) {
            if (questionModel.isAnswerCorrect()) correctCounter++;
        }
        return "Score: "+correctCounter + " from " + list.size();
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private final TextInputEditText et_question;
        private final TextInputEditText et_option1;
        private final TextInputEditText et_option2;
        private final RadioGroup rgOptions;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            et_question = itemView.findViewById(R.id.et_question);
            et_option1 = itemView.findViewById(R.id.et_option1);
            et_option2 = itemView.findViewById(R.id.et_option2);
            rgOptions = itemView.findViewById(R.id.rgOptions);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(QuestionModel medicineModel);
    }
}