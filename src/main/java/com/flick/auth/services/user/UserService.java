package com.flick.auth.services.user;

import com.flick.auth.repositories.UserRepository;
import io.vertx.ext.web.RoutingContext;

public interface UserService {
  public void getAllUsers(RoutingContext routingContext, UserRepository userRepository);

  public void addUser(RoutingContext routingContext, UserRepository userRepository);

  public void deleteUserById(RoutingContext routingContext, UserRepository userRepository);

  public void updateUserById(RoutingContext routingContext, UserRepository userRepository);

  public void getUserById(RoutingContext routingContext, UserRepository userRepository);

}
