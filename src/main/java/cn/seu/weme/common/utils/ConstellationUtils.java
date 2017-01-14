package cn.seu.weme.common.utils;

import com.google.common.base.Strings;

public class ConstellationUtils {
    public static final String[] constellationArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座",
            "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座" };

    public static final int[] constellationEdgeDay = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};

    public static String getConstellation(int month, int day) {
        if (day < constellationEdgeDay[month - 1]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        return constellationArr[11];
    }


    public static String getConstellation(String birthday) {
        if (Strings.isNullOrEmpty(birthday)) {
            return "";
        }
        String[] data = birthday.split("-");
        return getConstellation(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    public static void main(String[] args) {
        //"1992-09-30"
        String birthDay = "1992-09-30";
        String[] data = birthDay.split("-");

        System.out.println(getConstellation(Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

}
