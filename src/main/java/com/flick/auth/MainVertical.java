package com.flick.auth;

import com.flick.auth.repositories.UserRepository;
import com.flick.auth.services.user.UserServiceImpl;
import com.flick.auth.utility.RouterConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class MainVertical extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    RouterConfiguration routerConfiguration = new RouterConfiguration(new UserServiceImpl(), new UserRepository(vertx, config()));
    // Create router object
    Router router = routerConfiguration.configureRouter();

    vertx.createHttpServer().requestHandler(router).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

}
