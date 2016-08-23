package models;

import javax.persistence.*;
import com.avaje.ebean.Model;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
@Entity
@Table(name = "itemEntity")
public class WishListEntity extends Model {

    public static Finder<Long,WishListEntity> FINDER = new Finder<>(WishListEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "WishList")
    private Long id;
    private  String username;

    public WishListEntity() {
        this.id=null;
        this.username="NO MAN";
    }

    public WishListEntity(Long id) {
        this();
        this.id = id;
    }
    public WishListEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                "userrname=" + username +
                '}';
    }
}
