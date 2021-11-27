package fr.umlv.main.back.user;

import java.util.UUID;

public record UserCredentialDTO(UUID id, String password) {
}
