package mrandroid.app.activity.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import mrandroid.app.ViewModel;
import mrandroid.app.databinding.ActivityQuestionsNumberBinding;
import mrandroid.app.model.CourseModel;
import mrandroid.app.util.Constants;

public class QuestionsNumberActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private ActivityQuestionsNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        CourseModel courseModel= (CourseModel) getIntent().getSerializableExtra(Constants.COURSE_MODEL);

        binding.btnSubmit.setOnClickListener(view -> validateAndSubmit(courseModel));

        binding.btnSkip.setOnClickListener(view -> {
            viewModel.insertCourse(courseModel);
            finish();
        });
    }

    private void validateAndSubmit(CourseModel courseModel) {
        String questionNumber = Objects.requireNonNull(binding.etQuestionNumber.getText()).toString();

        boolean isEmpty = questionNumber.isEmpty();

        if (isEmpty) {
            Toast.makeText(this, "fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), AddExamActivity.class);
        intent.putExtra(Constants.COURSE_MODEL, courseModel);
        intent.putExtra(Constants.QUESTIONS_NUMBER, Integer.parseInt(questionNumber));
        intent.putExtra(Constants.IS_INSERTION, true);
        startActivity(intent);
        finish();
    }
}