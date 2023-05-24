# React-Native-CryptoJS
To  implement react native crypto js with java server to secure api key

##java:

Controller:


```java
 @GetMapping("/api/getPrivateKey")
  @ResponseBody
  public ResponseEntity<?> getPrivateKey(){
HashMap<String, String> result = new HashMap<>();

     try {
            HashMap<String, String> rate = testService.getEncryptandSecret( );
            if (rate != null) {
             return new ResponseEntity<>(rate, HttpStatus.ACCEPTED);
            } else {`
                result.put("message", "something Went Wrong");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("err", e.getLocalizedMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    } 
```

Service:

```java
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
    String toEncrypt = "API_Key";
    System.out.println("Plain text = " + toEncrypt);
    String encrStr = encrypt(toEncrypt, encodedBase64Key);
    HashMap<String, String> result = new HashMap<>();
    result.put("data", encrStr);
    result.put("privateKey", encodedBase64Key);
    System.out.println("result : " + result);
    return result;
}
```

**In React native:**

```javascript
import CryptoJS from "crypto-js";
const decryptString = async () => {
    try {
      console.log("decryptString====================");
      const response = await jsonserver.get(urls.getPrivateKey);
      console.log("response->" + response.data);
      const privateKey = response.data.privateKey;
      const encryptedString = response.data.data;
    var parsedBase64Key = CryptoJS.enc.Base64.parse(privateKey);
    var decryptedData = CryptoJS.AES.decrypt(
      encryptedString,
      parsedBase64Key,
      {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7,
      }
    );
    console.log("data--------------------   "+encryptedString);
    console.log("2---", +decryptedData);
    // console.log( “DecryptedData = “ + decryptedData );
    var decryptedText = decryptedData.toString(CryptoJS.enc.Utf8);
    setDecryptedCipherText(decryptedText);
      console.log("Decrypted String:---", decryptedText);
    } catch (err) {
      console.log(err);
    }
  };
```
