package fr.umlv.main.event;

import fr.umlv.main.user.User;

import java.util.UUID;

public record EventCredentialDTO(UUID id, User user) {
}
