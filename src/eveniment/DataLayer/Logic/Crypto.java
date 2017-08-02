
package eveniment.DataLayer.Logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Crypto {
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
