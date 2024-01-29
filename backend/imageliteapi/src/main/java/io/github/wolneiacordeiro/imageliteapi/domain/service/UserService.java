package io.github.wolneiacordeiro.imageliteapi.domain.service;

import io.github.wolneiacordeiro.imageliteapi.domain.AccessToken;
import io.github.wolneiacordeiro.imageliteapi.domain.entity.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AccessToken authenticate(String email, String password);
}
