package cn.seu.weme.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LCN on 2017-1-3.
 */
public class TokenProcessor {


    public static String generateToken(String msg) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());

            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


    private static String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(generateToken(i + ""));
        }
    }
}
