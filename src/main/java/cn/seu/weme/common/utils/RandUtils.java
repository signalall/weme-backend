package cn.seu.weme.common.utils;

import java.util.Random;

/**
 * Created by LCN on 2017-1-3.
 */
public class RandUtils {

    public static final char[] chars = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static Random random = new Random();

    public static String getRandomString(int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomString(5));
        }
    }
}
