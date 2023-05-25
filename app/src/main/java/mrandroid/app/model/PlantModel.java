package mrandroid.app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PlantModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String plantName;
    private int plantImage;
    private String color;
    private String description;
    private float price;
    private float rate;
    private int qty;

    public PlantModel(String plantName, int plantImage, String color, String description, float price, float rate, int qty) {
        this.plantName = plantName;
        this.plantImage = plantImage;
        this.color = color;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(int plantImage) {
        this.plantImage = plantImage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
