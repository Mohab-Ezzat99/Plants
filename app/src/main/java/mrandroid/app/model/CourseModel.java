package mrandroid.app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class CourseModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String courseName;
    private String videoUrl;
    private String teacherName;
    private String teacherPhone;
    private float price;
    private float rate;
    private String description;
    private List<QuestionModel> questionModels;

    public CourseModel(String courseName, String videoUrl, String teacherName, String teacherPhone, float price, float rate, String description, List<QuestionModel> questionModels) {
        this.courseName = courseName;
        this.videoUrl = videoUrl;
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;
        this.price = price;
        this.rate = rate;
        this.description = description;
        this.questionModels = questionModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", questionModels=" + questionModels +
                '}';
    }
}
