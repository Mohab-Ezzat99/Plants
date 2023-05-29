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
    private String type;
    private String description;
    private float price;
    private int qty = 1;

    public PlantModel(String plantName, int plantImage, String type, String description, float price, int qty) {
        this.plantName = plantName;
        this.plantImage = plantImage;
        this.type = type;
        this.description = description;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public CartModel toCartModel(int qty) {
        return new CartModel(id,plantName, plantImage, type, description, price, qty);
    }
}
