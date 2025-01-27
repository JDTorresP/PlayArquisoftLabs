package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import scala.collection.immutable.Range;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
@Entity
@Table(name = "productentity")
public class ProductEntity extends Model{

    public static Finder<Long,ProductEntity> FINDER = new Finder<>(ProductEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Product")
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Float price;
    private Boolean available;

    public ProductEntity() {
        this.id=null;
        this.name ="NO NAME";
        this.stock = -1;
        this.price = -1.00f;
        this.available = false;
    }

    public ProductEntity(Long id) {
        this();
        this.id = id;
    }

    public ProductEntity(Long id, String name, Integer stock, Float price, Boolean available,String description) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.available = available;
        this.description= description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", descripcion=" + description +
                ", available=" + available +
                '}';
    }
}