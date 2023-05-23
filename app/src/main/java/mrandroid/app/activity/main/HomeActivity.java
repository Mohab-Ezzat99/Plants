package mrandroid.app.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import mrandroid.app.ViewModel;
import mrandroid.app.activity.teacher.AddCourseActivity;
import mrandroid.app.adapter.CoursesAdapter;
import mrandroid.app.databinding.ActivityHomeBinding;
import mrandroid.app.model.CourseModel;
import mrandroid.app.util.Constants;

public class HomeActivity extends AppCompatActivity implements CoursesAdapter.OnItemClickListener {

    private ViewModel viewModel;
    private ActivityHomeBinding binding;
    private CoursesAdapter coursesAdapter = new CoursesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        coursesAdapter.setListener(this);
        binding.rvCourses.setAdapter(coursesAdapter);

        if(Constants.IS_TEACHER) binding.fabAdd.setVisibility(View.VISIBLE);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddCourseActivity.class);
            startActivity(intent);
        });

        fetchAllCourses();
    }

    private void fetchAllCourses() {
        viewModel.getAllCourses().observe(this, courseModels -> {
            coursesAdapter.setList(courseModels);
            coursesAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onItemClick(CourseModel courseModel) {
        Intent intent = new Intent(this, CourseDetailsActivity.class);
        intent.putExtra(Constants.COURSE_MODEL, courseModel);
        startActivity(intent);
    }
}