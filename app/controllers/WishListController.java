package controllers;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
import models.WishListEntity;
import play.mvc.Controller;
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class WishListController extends Controller {
    public CompletionStage<Result> getWishLists() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return WishListEntity.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(wishList -> {return ok(toJson(wishList));}
                );
    }
    public CompletionStage<Result> getWishList(Long idP) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return WishListEntity.FINDER.byId(idP); } ,jdbcDispatcher)
                .thenApply(wishList -> {return ok(toJson(wishList));}
                );
    }
    public CompletionStage<Result> createWishList(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nItem = request().body().asJson();
        WishListEntity wishList = Json.fromJson( nItem , WishListEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    wishList.save();
                    return wishList;
                }
        ).thenApply(
                WishListEntity -> {
                    return ok(Json.toJson(WishListEntity));
                }
        );
    }
    public CompletionStage<Result> deleteWishList(Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    WishListEntity wishList = WishListEntity.FINDER.byId(idP);

                    wishList.delete();
                    return wishList;
                }
        ).thenApply(
                WishListEntity -> {
                    return ok(Json.toJson(WishListEntity));
                }
        );
    }
    public CompletionStage<Result> updateWishList( Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        WishListEntity wishList = Json.fromJson( nProduct , WishListEntity.class ) ;
        WishListEntity antiguo = WishListEntity.FINDER.byId(idP);
        return CompletableFuture.supplyAsync(
                ()->{
                    antiguo.setId(wishList.getId());
                    antiguo.setUsername(wishList.getUsername());
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
