package fr.umlv.main.back.user;

import java.util.UUID;

/**
 * This class contains all the necessary information about an user that the
 * user needs to know.
 */
public record UserResponseDTO(UUID id, String username) {
}
