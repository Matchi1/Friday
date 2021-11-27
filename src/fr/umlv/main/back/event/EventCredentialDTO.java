package fr.umlv.main.back.event;

import fr.umlv.main.back.user.User;

import java.util.UUID;

public record EventCredentialDTO(UUID id, User user) {
}
