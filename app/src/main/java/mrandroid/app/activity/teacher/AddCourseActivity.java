package mrandroid.app.activity.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import mrandroid.app.databinding.ActivityAddCourseBinding;
import mrandroid.app.model.CourseModel;
import mrandroid.app.util.Constants;

public class AddCourseActivity extends AppCompatActivity {

    private ActivityAddCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(view -> {
            validateAndSubmit();
        });
    }

    private void validateAndSubmit() {
        String courseName = Objects.requireNonNull(binding.etCourseName.getText()).toString();
        String videoUrl = Objects.requireNonNull(binding.etVideoUrl.getText()).toString();
        String teacherName = Objects.requireNonNull(binding.etTeacherName.getText()).toString();
        String teacherPhone = Objects.requireNonNull(binding.etTeacherPhone.getText()).toString();
        String price = Objects.requireNonNull(binding.etPrice.getText()).toString();
        String rate = Objects.requireNonNull(binding.etRate.getText()).toString();
        String description = Objects.requireNonNull(binding.etDescription.getText()).toString();

        boolean isEmpty = courseName.isEmpty() || videoUrl.isEmpty() || teacherName.isEmpty() ||
                teacherPhone.isEmpty() || price.isEmpty() || rate.isEmpty() || description.isEmpty();

        if (isEmpty) {
            Toast.makeText(this, "fields are required", Toast.LENGTH_SHORT).show();
            return;
        }


        CourseModel courseModel = new CourseModel(
                courseName,
                videoUrl,
                teacherName,
                teacherPhone,
                Float.parseFloat(price),
                Float.parseFloat(rate),
                description,
                null
        );

        Intent intent = new Intent(getBaseContext(), QuestionsNumberActivity.class);
        intent.putExtra(Constants.COURSE_MODEL, courseModel);
        startActivity(intent);
        finish();
    }
}