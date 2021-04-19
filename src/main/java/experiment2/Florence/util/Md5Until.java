package experiment2.Florence.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Md5Until {
    public static String getMd5(String pInput) {
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            md5Digest.update(pInput.getBytes());
            BigInteger md5HashInt = new BigInteger(1, md5Digest.digest());
            return String.format("%1$032X", md5HashInt);
        } catch (NoSuchAlgorithmException lException) {
            throw new RuntimeException(lException);
        }
    }
    public static int getRandom(int l,int r){
        Random random = new Random();
        return (int) (random.nextDouble()*(r-l)+l);
    }

}