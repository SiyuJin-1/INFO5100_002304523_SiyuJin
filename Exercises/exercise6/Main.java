import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws Exception {
    Person Alice = new Person();
    Person Bob = new Person();

    String message = "We are students in INFO 5100!";
    System.out.println("\n--------------------------- Original Message ----------------------------\n");
    System.out.println(message);

    // AES encryption and decryption
    System.out.println("\n--------------------------- AES Encryption and Decryption ----------------------------\n");
    byte[] iv = new byte[12]; 
    new SecureRandom().nextBytes(iv);
    byte[] AESEncrypted = Encryption.encryptAES(message, Alice.getAESKey(), iv);
    String AESDecrypted = Encryption.decryptAES(AESEncrypted, Alice.getAESKey(), iv);
    System.out.println("After AES Encrytion: " + AESEncrypted);
    System.out.println("After AES Decrytion: " + AESDecrypted);

    // RSA encryption and decryption
    System.out.println("\n--------------------------- RSA Encryption and Decryption ----------------------------\n");
    byte[] RSAEncrypted = Encryption.encryptRSA(message, Bob.getPublicKey());
    String RSADecrypted = Encryption.decryptRSA(RSAEncrypted, Bob.getPrivateKey());
    System.out.println("After RSA Encrytion: " + RSAEncrypted);
    System.out.println("After RSA Decrytion: " + RSADecrypted);

    // Signature generation and verification
    System.out.println("\n--------------------------- Signature Generation and Verification ----------------------------\n");
    byte[] signature = SignatureValid.signMessage(message, Alice.getPrivateKey());
    boolean verified = SignatureValid.verifySignature(message, signature, Alice.getPublicKey());
    System.out.println("Signature Verification: " + verified);
}
}
