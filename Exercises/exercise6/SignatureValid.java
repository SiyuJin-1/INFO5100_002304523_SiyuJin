import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureValid {
    // Sign a message with a private key
    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        // Create a signature object for SHA256withRSA
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the signature with the private key and sign the message
        signature.initSign(privateKey);
        signature.update(message.getBytes()); 
        byte[] signMessage = signature.sign();
        return signMessage;
    }

    // Verify a signature with a public key
    public static boolean verifySignature(String message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        // Create a signature object for SHA256withRSA
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the signature with the public key and verify the message
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        boolean verifiedMessage = signature.verify(signatureBytes);
        return verifiedMessage;
    }

}
