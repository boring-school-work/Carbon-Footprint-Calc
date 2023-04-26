import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Test2
 */
public class Test2 {

    private static final int SIZE = 128;
    private static final int ITERATIONS = 18;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static boolean authenticate(char[] password, String token) {
        byte[] hash = Base64.getUrlDecoder().decode(token);
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8); // extract salt
        byte[] check = pbkdf2(password, salt); // derive key from password
        int zero = 0; // count zero bytes at the end of the hash

        for (int idx = 0; idx < check.length; ++idx)
            zero |= hash[salt.length + idx] ^ check[idx];

        return zero == 0;
    }

    public static byte[] pbkdf2(char[] passcode, byte[] salt) {
        KeySpec spec = new PBEKeySpec(passcode, salt, ITERATIONS, SIZE);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Missing algorithm: " + ALGORITHM, e);
        } catch (InvalidKeySpecException e) {
            throw new IllegalStateException("Invalid SecretKeyFactory", e);
        }
    }

    /**
     * Returns a password token after hashing.
     *
     * @param password the password to hash
     *
     * @return the password token
     *
     */
    public static String hash(char[] password) {
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        byte[] salt = new byte[SIZE / 8];
        RANDOM.nextBytes(salt);

        byte[] derivedKey = pbkdf2(password, salt); // derive the key
        byte[] hash = new byte[salt.length + derivedKey.length]; // create a hash array with length of salt + derived
                                                                 // key

        System.arraycopy(salt, 0, hash, 0, salt.length); // copy salt to hash
        System.arraycopy(derivedKey, 0, hash, salt.length, derivedKey.length); // copy derived key to hash

        return enc.encodeToString(hash);
    }

    public static void main(String[] args) {
        // System.out.println(hash("password".toCharArray()));

        System.out.println(authenticate("password".toCharArray(), "PaA0WYqgnIwyFumsXaiqKw58JB7Nh-hexTzyeooPnyY"));
    }
}

// authData.txt -> authentification data
// vehicleData.txt -> vehicle data