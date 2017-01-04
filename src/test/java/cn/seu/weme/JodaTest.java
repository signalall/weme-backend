package cn.seu.weme;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

/**
 * Created by LCN on 2016-12-10.
 */
public class JodaTest {

    @Test
    public void test(){
        Date juDate = new Date();
        DateTime dt = new DateTime(juDate);
        int month = dt.getMonthOfYear();  // where January is 1 and December is 12
        int year = dt.getYear();

        System.out.println("month : " + month + ";year : " + year);

        DateTime dt1 = new DateTime();
        DateTime year2000 = dt.withYear(2000);
        DateTime twoHoursLater = dt1.plusHours(2);
        System.out.println(twoHoursLater);
        System.out.println(year2000);



        DateTime dt2 = new DateTime();
        String monthName = dt2.monthOfYear().getAsText();
        String frenchShortName = dt2.monthOfYear().getAsShortText(Locale.CHINESE);
        boolean isLeapYear = dt2.year().isLeap();
        DateTime rounded = dt2.dayOfMonth().roundFloorCopy();


        System.out.println("monthName: " + monthName);
        System.out.println("ChineseShortName: " + frenchShortName);
        System.out.println("rounded: " + rounded);



        DateTime dt3 = new DateTime(2005, 3, 26, 12, 0, 0, 0);
        DateTime plusPeriod = dt3.plus(Period.days(1));
        DateTime plusDuration = dt3.plus(new Duration(24L*60L*60L*1000L));

        System.out.println(dt3);
        System.out.println(plusPeriod);


    }



    @Test
    public void testStartEndOfDay(){
        //获取今天还剩多长时间
        DateTime nowTime = new DateTime();
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        System.out.println(endOfDay.getMillis() - nowTime.getMillis());
        DateTime startOfDay = nowTime.withTimeAtStartOfDay();
        System.out.println(startOfDay);
        System.out.println(endOfDay);

    }
}
