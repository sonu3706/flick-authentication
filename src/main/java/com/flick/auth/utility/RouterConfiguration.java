package com.flick.auth.utility;

import com.flick.auth.repositories.UserRepository;
import com.flick.auth.services.user.UserService;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/*
 * All API's are defined here. whenever new endpoints are created it has to
 * be defined here as routes
 *
 * */
public class RouterConfiguration {

  private final UserService userService;
  private final UserRepository userRepository;

  public RouterConfiguration(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  public Router configureRouter() {
    Vertx vertx = Vertx.vertx();
    Router router = Router.router(vertx);

    /* Get all users */
    router.get("/api/users").handler(routingContext -> {
      this.userService.getAllUsers(routingContext, userRepository);
    });

    router.route("/api/users").handler(BodyHandler.create());
    router.post("/api/users").consumes("application/json").handler(routingContext -> {
      userService.addUser(routingContext, userRepository);
    });

    router.delete("/api/users/:id").handler(routingContext -> {
      userService.deleteUserById(routingContext, userRepository);
    });

    router.route("/api/users").handler(BodyHandler.create());
    router.put("/api/users").consumes("application/json").handler(routingContext -> {
      userService.updateUserById(routingContext, userRepository);
    });

    router.get("/api/users/:id").handler(routingContext -> {
      userService.getUserById(routingContext, userRepository);
    });
    return router;
  }
}
