package fr.umlv.main;


import org.apache.http.HttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

public record UserResponseDTO(UUID id, String username) {
    @PostMapping("/user")
    HttpResponse<UserResponseDTO> saveUser(@ResponseBody @Validated UserSaveDTO input) {
        return HttpResponse.

    }
}
