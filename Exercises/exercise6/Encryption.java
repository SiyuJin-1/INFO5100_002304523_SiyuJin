import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class Encryption {
    // AES encryption
    public static byte[] encryptAES(String message, SecretKey key, byte[] iv) throws Exception {
        // Create a cipher object for AES encryption with GCM mode and no padding
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Set Parameters for encryption
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);

        // Initializes the encryptor and encrypts the message
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        return encryptedMessage;
    }

    // AES decryption
    public static String decryptAES(byte[] ciphertext, SecretKey key, byte[] iv) throws Exception {
        // Create a cipher object for AES decryption with GCM mode and no padding
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Set Parameters for decryption
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);

        // Initializes the decryptor and decrypts the message
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);
        byte[] decrypted = cipher.doFinal(ciphertext);
        String decryptedMessage = new String(decrypted);
        return decryptedMessage;
    }

    // RSA encryption
    public static byte[] encryptRSA(String message, PublicKey publicKey) throws Exception {
        // Create a cipher object for RSA encryption with PKCS1 padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Initializes the encryptor and encrypts the message
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        return encryptedMessage;
    }

    // RSA decryption
    public static String decryptRSA(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        // Create a cipher object for RSA decryption with PKCS1 padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Initializes the decryptor and decrypts the message
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(ciphertext);
        String decryptedMessage = new String(decrypted);
        return decryptedMessage;
    }

}
