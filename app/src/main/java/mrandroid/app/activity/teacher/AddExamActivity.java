package mrandroid.app.activity.teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import mrandroid.app.ViewModel;
import mrandroid.app.adapter.QuestionsAdapter;
import mrandroid.app.databinding.ActivityAddExamBinding;
import mrandroid.app.model.CourseModel;
import mrandroid.app.model.QuestionModel;
import mrandroid.app.util.Constants;

public class AddExamActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private ActivityAddExamBinding binding;
    private QuestionsAdapter questionsAdapter = new QuestionsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        CourseModel courseModel = (CourseModel) getIntent().getSerializableExtra(Constants.COURSE_MODEL);
        int questionsNumber = (int) getIntent().getSerializableExtra(Constants.QUESTIONS_NUMBER);
        boolean isInsertion = (boolean) getIntent().getSerializableExtra(Constants.IS_INSERTION);

        binding.rvQuestions.setAdapter(questionsAdapter);

        binding.btnSubmit.setOnClickListener(view -> {
            if (isInsertion) validateAndInsert(courseModel);
            else validateAndScore();
        });

        questionsAdapter.setList(courseModel.getQuestionModels());

        if (courseModel.getQuestionModels()!=null)
            questionsAdapter.setList(courseModel.getQuestionModels());
        else {
            questionsAdapter.setList(initQuestionsList(questionsNumber));
            questionsAdapter.setCanEdit(true);
        }
    }

    private ArrayList<QuestionModel> initQuestionsList(int questionsNumber) {
        ArrayList<QuestionModel> questionModels = new ArrayList<>();
        for (int i = 0; i < questionsNumber; i++) {
            questionModels.add(new QuestionModel("", "", "", -1, -1));
        }
        return questionModels;
    }

    private void validateAndInsert(CourseModel courseModel) {
        questionsAdapter.notifyDataSetChanged();
        questionsAdapter.setList(questionsAdapter.getList());

        if (questionsAdapter.isFieldsRequired()) {
            Toast.makeText(this, "Fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (questionsAdapter.isAnswerRequired()) {
            Toast.makeText(this, "Answers are required", Toast.LENGTH_SHORT).show();
            return;
        }

        courseModel.setQuestionModels(questionsAdapter.getList());
        viewModel.insertCourse(courseModel);
        finish();
    }

    private void validateAndScore() {
        if (questionsAdapter.isFieldsRequired()) {
            Toast.makeText(this, "Fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (questionsAdapter.isAnswerRequired()) {
            Toast.makeText(this, "Answers are required", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.tvScore.setVisibility(View.VISIBLE);
        binding.tvScore.setText(questionsAdapter.calculateScore());
    }
}