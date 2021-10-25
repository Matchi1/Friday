package fr.umlv.main;


import org.apache.http.HttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.UUID;

public record UserResponse(UUID id, String username) {
    private final Calendar entity;

    @PostMapping("/user")
    HttpResponse<UserResponse> saveUser(@ResponseBody @Validated UserSave input) {
        var newUser = entity.saveUser()
        return HttpResponse.

    }
}
