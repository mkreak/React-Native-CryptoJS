import CryptoJS from "crypto-js";


const decryptString = async () => {
    try {
      console.log("decryptString====================");
      const response = await jsonserver.get(urls.getPrivateKey);
      console.log("response=====================");
      console.log("response->" + JSON.stringify(response));
      console.log("---------------------------------------------------------");
      console.log("response->" + response.data);
      const privateKey = response.data.privateKey;
      const encryptedString = response.data.data;
      //var encryptedBase64Key = "aXRzaG91bGRiZTE2Y2hhcg==";
    var parsedBase64Key = CryptoJS.enc.Base64.parse(privateKey);
    console.log("privateKey--------------------   "+privateKey);
    console.log("1---", +parsedBase64Key);
    //var encryptedCipherText = "wbsl6Tegfc7mexCHUYPS+dON3BWfgU+tD1nkiHiZ4LyrHlUXlWsfeInWMDaZO39z"; // or encryptedData;
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
    // this is the decrypted data as a string
    var decryptedText = decryptedData.toString(CryptoJS.enc.Utf8);
    setDecryptedCipherText(decryptedText);


      console.log("Decrypted String:---", decryptedText);
      // console.log('Decrypted String2:', decryptedString2);
    } catch (err) {
      console.log(err);
    }
  };
