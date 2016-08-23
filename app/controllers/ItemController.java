package controllers;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import models.ItemEntity;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class ItemController extends Controller {
    public CompletionStage<Result> getItems() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return ItemEntity.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(itemEntities -> {return ok(toJson(itemEntities));}
                );
    }
    public CompletionStage<Result> getItem(Long idP) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return ItemEntity.FINDER.byId(idP); } ,jdbcDispatcher)
                .thenApply(itemEntities -> {return ok(toJson(itemEntities));}
                );
    }
    public CompletionStage<Result> createItem(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nItem = request().body().asJson();
        ItemEntity item = Json.fromJson( nItem , ItemEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    item.save();
                    return item;
                }
        ).thenApply(
                ItemEntity -> {
                    return ok(Json.toJson(ItemEntity));
                }
        );
    }
    public CompletionStage<Result> deleteItem(Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    ItemEntity item = ItemEntity.FINDER.byId(idP);
                    item.delete();
                    return item;
                }
        ).thenApply(
                ItemEntity -> {
                    return ok(Json.toJson(ItemEntity));
                }
        );
    }
    public CompletionStage<Result> updateItem( Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        ItemEntity product = Json.fromJson( nProduct , ItemEntity.class ) ;
        ItemEntity antiguo = ItemEntity.FINDER.byId(idP);
        return CompletableFuture.supplyAsync(
                ()->{
                    antiguo.setId(product.getId());
                    antiguo.setPrice(product.getPrice());
                    antiguo.setIdProducto(product.getIdProducto());
                    antiguo.setIdWishList(product.getIdWhishList());
                    antiguo.setQuantity(product.getQuantity());
                    antiguo.update();
                    return antiguo;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }
}
