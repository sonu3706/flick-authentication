package com.flick.auth.services.user;

import com.flick.auth.repositories.UserRepository;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.RoutingContext;

import java.security.AuthProvider;

public class UserServiceImpl implements UserService {

  @Override
  public void getAllUsers(RoutingContext routingContext, UserRepository userRepository) {

  }

  @Override
  public void addUser(RoutingContext routingContext, UserRepository userRepository) {
    userRepository.saveData(routingContext, stringAsyncResult -> {
      if (stringAsyncResult.succeeded()) {
        routingContext.response().setStatusCode(201).putHeader("Content-type", "application/json; charset=utf-8").end(stringAsyncResult.result());
      } else {
        routingContext.fail(stringAsyncResult.cause());
      }
    });
  }

  @Override
  public void deleteUserById(RoutingContext routingContext, UserRepository userRepository) {

  }

  @Override
  public void updateUserById(RoutingContext routingContext, UserRepository userRepository) {
    userRepository.updateUserById(routingContext, stringAsyncResult -> {
      if (stringAsyncResult.succeeded()) {

      } else {
        routingContext.fail(stringAsyncResult.cause());
      }
    });
  }

  @Override
  public void getUserById(RoutingContext routingContext, UserRepository userRepository) {
    userRepository.getUserById(routingContext.request().getParam("id"), stringAsyncResult -> {
      if (stringAsyncResult.succeeded()) {
        JWTAuthOptions config = new JWTAuthOptions().setKeyStore(new KeyStoreOptions().setPath("keystore.jceks").setPassword("secret"));
        AuthenticationProvider provider = JWTAuth.create(Vertx.vertx(), config);
        System.out.println(stringAsyncResult.result());
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("user", stringAsyncResult.result());
        System.out.println(jsonObject);
        routingContext.response().setStatusCode(200).putHeader("Content-type", "application/json").end(jsonObject.encodePrettily());
        routingContext.fail(stringAsyncResult.cause());
      }
    });
  }
}
