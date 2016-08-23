package controllers;

/**
 * Created by jd.torres11 on 20/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import models.ProductEntity;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class ProductController extends Controller{

    public CompletionStage<Result> getProducts() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return ProductEntity.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(productEntities -> {return ok(toJson(productEntities));}
                );
    }
    public CompletionStage<Result> getProduct(Long idP) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return ProductEntity.FINDER.byId(idP); } ,jdbcDispatcher)
                .thenApply(productEntities -> {return ok(toJson(productEntities));}
                );
    }

    public CompletionStage<Result> createProduct(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        ProductEntity product = Json.fromJson( nProduct , ProductEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    product.save();
                    return product;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }
    public CompletionStage<Result> deleteProduct(Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;


        return CompletableFuture.supplyAsync(
                ()->{
                    ProductEntity product = ProductEntity.FINDER.byId(idP);
                    product.delete();
                    return product;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }

    public CompletionStage<Result> updateProduct( Long idP){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        ProductEntity product = Json.fromJson( nProduct , ProductEntity.class ) ;
        ProductEntity antiguo = ProductEntity.FINDER.byId(idP);
        return CompletableFuture.supplyAsync(
                ()->{

                    antiguo.setId(product.getId());
                    antiguo.setName(product.getName());
                    antiguo.setAvailable(product.getAvailable());
                    antiguo.setPrice(product.getPrice());
                    antiguo.setStock(product.getStock());
                    antiguo.setDescription(product.getDescription());
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
