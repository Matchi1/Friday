package fr.umlv.main.user;

import java.util.UUID;

public record UserUpdatePasswordDTO(UUID id, String newPassword) {
}
