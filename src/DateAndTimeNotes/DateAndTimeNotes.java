package DateAndTimeNotes;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

public class DateAndTimeNotes {

    public static void main(String[] args) {

        //LocalDateTime类
        //now():获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);//2021-08-13
        System.out.println(localTime);//22:35:48.204
        System.out.println(localDateTime);//2021-08-13T22:35:48.204

        //of():设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime setLocalDateTime = LocalDateTime.of(2021, 8, 20, 9, 20, 25);
        System.out.println(setLocalDateTime);//2021-08-20T09:20:25

        //getXxx()：获取相关的属性
        System.out.println(localDateTime.getDayOfMonth());//一个月第几天
        System.out.println(localDateTime.getDayOfWeek());//星期几
        System.out.println(localDateTime.getMonth());//星期几的数字
        System.out.println(localDateTime.getMonthValue());//月份数字
        System.out.println(localDateTime.getHour());//小时数
        System.out.println(localDateTime.getMinute());//分钟数
        System.out.println(localDateTime.getSecond());//秒数

        //体现不可变性
        //withXxx():设置相关的属性
        LocalDate setLocalDate = localDate.withDayOfYear(20);
        setLocalDate = localDate.withDayOfMonth(10);//设置日期数
        System.out.println(localDate);
        System.out.println(setLocalDate);

        setLocalDateTime = localDateTime.withHour(4);//加小时数
        System.out.println(localDateTime);
        System.out.println(setLocalDateTime);

        setLocalDateTime = localDateTime.plusMonths(3);//加月份数
        System.out.println(localDateTime);
        System.out.println(setLocalDateTime);

        setLocalDateTime = localDateTime.minusDays(6);//减少日期数
        System.out.println(localDateTime);
        System.out.println(setLocalDateTime);

        System.out.println("************************************");


        //Instant类

        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant);//2021-08-13T14:35:48.204Z

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2021-08-13T22:35:48.204+08:00

        //toEpochMilli():获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数  ---> Date类的getTime()
        long milli = instant.toEpochMilli();
        System.out.println(milli);//1628865348204

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例  -->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1550475314878L);
        System.out.println(instant1);//2019-02-18T07:35:14.878Z

        System.out.println("************************************");



        //DateTimeFormatter类

        //通过 预定义的标准格式 进行格式化或者解析：
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期-->字符串
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);//2021-08-13T22:44:41.966
        System.out.println(str1);//2021-08-13T22:44:41.966
        //解析：字符串 -->日期
        TemporalAccessor parse = formatter.parse("2021-08-13T22:44:41.966");
        System.out.println(parse);//{},ISO resolved to 2021-08-13T22:44:41.966

        //通过 本地化相关的格式 进行格式化或者解析：
        //ofLocalizedDateTime()格式
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        //格式化
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);//2021年8月13日 下午10时52分21秒

        //ofLocalizedDate()格式
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        //格式化
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2021-8-13


        //通过 自定义的格式 进行格式化或者解析：
        //ofPattern()
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2021-08-13 10:54:47

        //解析
        TemporalAccessor accessor = formatter3.parse("2020-10-12 08:25:20");
        System.out.println(accessor);//{MinuteOfHour=25, HourOfAmPm=8, MilliOfSecond=0, MicroOfSecond=0, SecondOfMinute=20, NanoOfSecond=0},ISO resolved to 2020-10-12




    }
}
