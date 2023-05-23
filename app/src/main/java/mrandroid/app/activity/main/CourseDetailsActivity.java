package mrandroid.app.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mrandroid.app.activity.teacher.AddExamActivity;
import mrandroid.app.databinding.ActivityCourseDetailsBinding;
import mrandroid.app.model.CourseModel;
import mrandroid.app.util.Constants;

public class CourseDetailsActivity extends AppCompatActivity {

    private ActivityCourseDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CourseModel courseModel = (CourseModel) getIntent().getSerializableExtra(Constants.COURSE_MODEL);

        binding.tvCourseName.setText(courseModel.getCourseName());
        binding.tvTeacherName.setText(courseModel.getTeacherName());
        binding.tvDescription.setText(courseModel.getDescription());
        binding.tvPrice.setText(courseModel.getPrice() + " SAR");
        binding.ratingBar.setRating(courseModel.getRate());
        binding.btnContact.setText("Contact: " + courseModel.getTeacherPhone());
        setupVideo(courseModel.getVideoUrl());

        if(courseModel.getQuestionModels()==null) {
            binding.btnExam.setVisibility(View.GONE);
        }

        binding.btnExam.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AddExamActivity.class);
            intent.putExtra(Constants.COURSE_MODEL, courseModel);
            intent.putExtra(Constants.QUESTIONS_NUMBER, -1);
            intent.putExtra(Constants.IS_INSERTION, false);
            startActivity(intent);
        });

        binding.btnContact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + courseModel.getTeacherPhone()));
            startActivity(intent);
        });

    }

    private void setupVideo(String videoUrl) {
        String videoId = getVideoIdFromYoutubeUrl(videoUrl);
        getLifecycle().addObserver(binding.FAboutUsYoutubeView);
        binding.FAboutUsYoutubeView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                onVideoId(youTubePlayer, videoId);
                youTubePlayer.loadVideo(videoId, 0f);
                youTubePlayer.play();
            }
        });
    }

    public String getVideoIdFromYoutubeUrl(String youtubeUrl) {
        String pattern = "https?://(?:[0-9A-Z-]+\\.)?(?:youtu\\.be/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|</a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(youtubeUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "tThe330YfhM";
    }
}