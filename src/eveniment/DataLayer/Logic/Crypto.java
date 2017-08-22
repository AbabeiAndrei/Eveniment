
package eveniment.DataLayer.Logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Crypto {
    //metoda ce converteste un string in md5 (https://en.wikipedia.org/wiki/MD5)
    //algorithm https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
    public static String HashMd5(String source){
        StringBuilder hexString = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }   
            
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
