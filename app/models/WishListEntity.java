package models;

import javax.persistence.*;
import com.avaje.ebean.Model;
import java.util.List;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
@Entity
@Table(name = "itemEntity")
public class WishListEntity extends Model {

    public static Finder<Long,ItemEntity> FINDER = new Finder<>(ItemEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "WishList")
    private Long id;
    private List<Long> id_items;
    private Float totalPrice;

    public WishListEntity() {
        this.id=null;
        this.totalPrice = 0.0f;
        this.id_items=null;
    }

    public WishListEntity(Long id) {
        this();
        this.id = id;
    }
    public WishListEntity(Long id, List<Long> id_items) {
        this.id = id;
        this.id_items=id_items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getItems() { return id_items; }

    public void setItem(ItemEntity item) {
        this.id_items.add(item.getId()); totalPrice +=item.getPrice();
    }
    public void deleteItem(ItemEntity item) {
        this.id_items.remove(item.getId()); totalPrice -=item.getPrice();
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", id items=" + id_items.toString() +
                '}';
    }
}
