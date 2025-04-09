import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class Person {
    SecretKey aesKey; // AES secret key
    KeyPair rsaKeyPair; // RSA key pair(public key + private key)

    public Person() throws Exception {
        // Create AES secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        // Initialize the key with a key size of 256 bits and generate the key
        keyGen.init(256);
        aesKey = keyGen.generateKey();

        // Create RSA key pair
        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");

        // Initialize the key pair generator with a key size of 2048 bits and generate the key pair
        rsaGen.initialize(2048);
        rsaKeyPair = rsaGen.generateKeyPair();
    }

    // Get RSA public key
    public PublicKey getPublicKey() {
        return rsaKeyPair.getPublic();
    }

    // Get RSA private key
    public PrivateKey getPrivateKey() {
        return rsaKeyPair.getPrivate();
    }

    // Get AES secret key
    public SecretKey getAESKey() {
        return aesKey;
    }
}
