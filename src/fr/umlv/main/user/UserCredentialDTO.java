package fr.umlv.main.user;

import java.util.UUID;

public record UserCredentialDTO(UUID id, String password) {
}
