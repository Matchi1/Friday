package fr.umlv.back.crypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CryptPasswordTest {
    @Test
    public void shouldHaveSameCryptValue() {
        var encryptor = new CryptPassword();
        var password = "azerty";
        var encryptedPassword = encryptor.hash(password);
        Assertions.assertEquals(encryptedPassword, encryptor.hash(password));
    }

    @Test
    public void shouldNotHaveSameCryptValue() {
        var encryptor = new CryptPassword();
        var password = "azerty";
        var encryptedPassword = encryptor.hash(password);
        Assertions.assertNotEquals(encryptedPassword, encryptor.hash("azert"));
    }

    @Test
    public void shouldBeCrypted() {
        var encryptor = new CryptPassword();
        var password = "azerty";
        Assertions.assertNotEquals(encryptor.hash(password), password);
    }

    @Test
    public void ShouldThrowNPE() {
        var encryptor = new CryptPassword();
        Assertions.assertThrows(NullPointerException.class, () -> encryptor.hash(null));
    }
}
