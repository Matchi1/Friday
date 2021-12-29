package fr.umlv.back.event;

import fr.umlv.back.user.User;

import java.util.UUID;

/**
 * This class provides information about the id of the event its corresponding
 * user
 */
public record EventCredentialDTO(UUID id, User user) {
}
