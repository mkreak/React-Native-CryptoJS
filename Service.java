import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

private static final String ALGO = "AES";

public static String encrypt(String Data, String secret) throws Exception {
    Key key = generateKey(secret);
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.ENCRYPT_MODE, key);
    byte[] encVal = c.doFinal(Data.getBytes());
    String encryptedValue = Base64.getEncoder().encodeToString(encVal);
    return encryptedValue;
}

private static Key generateKey(String secret) throws Exception {
    byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
    Key key = new SecretKeySpec(decoded, ALGO);
    return key;
}

public static String encodeKey(String str) {
    byte[] encoded = Base64.getEncoder().encode(str.getBytes());
    return new String(encoded);
}

public HashMap<String, String> getEncryptandSecret() throws Exception {
    String secretKey = "itshouldbe16char";
    String encodedBase64Key = encodeKey(secretKey);
    System.out.println("EncodedBase64Key = " + encodedBase64Key);
    String toEncrypt = "rzp_test_KvRSBZLSjdT5ST";
    System.out.println("Plain text = " + toEncrypt);
    String encrStr = encrypt(toEncrypt, encodedBase64Key);
    System.out.println("Cipher Text: Encryption of str = " + encrStr);
    HashMap<String, String> result = new HashMap<>();
    result.put("data", encrStr);
    result.put("privateKey", encodedBase64Key);
    System.out.println("privateKey : " + encodedBase64Key + " encrStr " + encrStr);
    System.out.println("result : " + result);
    return result;
}
