package fr.umlv.main.back.crypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CryptTest {
    @Test
    public void shouldHaveSameCryptValue() {
        var crypter = new CryptPassword();
        var password = "azerty";
        var cryptedPassword = crypter.hash(password);
        Assertions.assertEquals(cryptedPassword, crypter.hash(password));
    }

    @Test
    public void shouldNotHaveSameCryptValue() {
        var crypter = new CryptPassword();
        var password = "azerty";
        var cryptedPassword = crypter.hash(password);
        Assertions.assertNotEquals(cryptedPassword, crypter.hash("azert"));
    }

    @Test
    public void shouldBeCrypted() {
        var crypter = new CryptPassword();
        var password = "azerty";
        Assertions.assertNotEquals(crypter.hash(password), password);
    }

    @Test
    public void ShouldThrowNPE() {
        var crypter = new CryptPassword();
        Assertions.assertThrows(NullPointerException.class, () -> crypter.hash(null));
    }
}
