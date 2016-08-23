package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import java.math.BigInteger;

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
    private Long id_product;
    private Long id_wishList;
    private Float price;
    private Integer quantity;


    public ItemEntity() {
        this.id=null;
        this.id_product=null;
        this.price = -1.00f;
        this.id_wishList=null;
        this.quantity=-1;
    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }
    public ItemEntity(Long id, Long id_product, Float price, Long id_wishList,Integer quantity) {
        this.id = id;
        this.price = price;
        this.id_product=id_product;
        this.id_wishList=id_wishList;
        this.quantity=quantity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdWhishList() {
        return id_wishList;
    }

    public void setIdWishList(Long id_wishList) {
        this.id_wishList = id_wishList;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", id product=" + id_product +
                ", id wishList=" + id_wishList +
                '}';
    }
}
