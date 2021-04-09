package com.flick.auth;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;

public class DriverClass {
  public static void main(String[] args) {
//    Launcher.executeCommand("run", MainVertical.class.getName());
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVertical(), stringAsyncResult -> {
      System.out.println("MainVertical deployed");
    });
  }
}
