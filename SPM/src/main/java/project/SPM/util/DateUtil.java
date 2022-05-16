package project.SPM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 날짜, 시간 출력
     * @param fm 날짜 출력 형식
     */
    public static String getDateTime(String fm) {

        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat(fm);

        return date.format(today);
    }

    /**
     * 날짜, 시간 출력
     * @return 기본 값은 년, 월, 일일
     */
    public static String getDateTime() {
        return getDateTime("yyyy.MM.dd");
    }
}
