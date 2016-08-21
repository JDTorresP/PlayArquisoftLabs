package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
@Entity
@Table(name = "itemEntity")
public class ItemEntity extends Model{
    public static Finder<Long,ItemEntity> FINDER = new Finder<>(ItemEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Item")
    private Long id;
    private String name;
    private Long id_product;
    private Float price;

    public ItemEntity() {
        this.id=null;
        this.id_product=null;
        this.name ="NO NAME";
        this.price = -1.00f;
    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }
    public ItemEntity(Long id, String name, Long id_product, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_product=id_product;
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

    public Long getIdProducto() { return id_product; }

    public void setIdProducto(Long id_product) {
        this.id_product = id_product;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id product=" + id_product +
                '}';
    }
}
