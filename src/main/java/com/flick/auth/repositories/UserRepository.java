package com.flick.auth.repositories;

import com.flick.auth.model.User;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {
  private static final String MONGO_DB_URL = "mongodb://localhost:27017";
  private static final String MONGO_DB_NAME = "flick";
  private static final String USER_COLLECTION_NAME = "users";
  private final MongoClient mongoClient;

  public UserRepository(Vertx vertx, JsonObject config) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.put("connection_string", "mongodb://localhost:27017");
    jsonObject.put("dbName", "flick");
    this.mongoClient = MongoClient.createShared(vertx, config);
  }


  public void saveData(RoutingContext routingContext, Handler<AsyncResult<String>> resultHandler) {
    this.mongoClient.save("users", routingContext.getBodyAsJson(), resultHandler);
  }

  public void deleteData(RoutingContext routingContext, Handler<AsyncResult<String>> resultHandler) {

  }

  public void getUserById(String id, Handler<AsyncResult<User>> resultHandler) {
    JsonObject jsonObject = new JsonObject().put("_id", id);
    this.mongoClient.find("users", jsonObject, res -> {
      if (res.succeeded()) {
        List<JsonObject> jsonObjectList = res.result();
        User user = jsonObjectList.stream().map(User::new).collect(Collectors.toList()).get(0);
        resultHandler.handle(Future.succeededFuture(user));
      } else {
        resultHandler.handle(Future.failedFuture(res.cause()));
      }
    });
  }

  public void updateUserById(RoutingContext routingContext, Handler<AsyncResult<User>> resultHandler) {
    JsonObject jsonObject = new JsonObject().put("_id", routingContext.request().getParam("id"));
    JsonObject update = new JsonObject().put("$set", routingContext.getBodyAsJson());
    this.mongoClient.updateCollection("users", jsonObject, update, res -> {
      if (res.succeeded()) {
        System.out.println("update");
      } else {
        resultHandler.handle(Future.failedFuture(res.cause()));
      }
    });

  }
}
