package br.com.sgfly.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static List<YearMonth> getMonthsBetween(LocalDate startDate, LocalDate endDate) {
        List<YearMonth> monthsBetween = new ArrayList<>();

        YearMonth startYearMonth = YearMonth.from(startDate);
        YearMonth endYearMonth = YearMonth.from(endDate);

        long monthsCount = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);

        for (long i = 0; i <= monthsCount; i++) {
            YearMonth currentMonth = startYearMonth.plusMonths(i);
            monthsBetween.add(currentMonth);
        }

        return monthsBetween;
    }

    public static LocalDate getInitialDate(YearMonth yearMonth) {
        return yearMonth.atDay(1);
    }

    public static LocalDate getEndDate(YearMonth yearMonth) {
        return yearMonth.atEndOfMonth();
    }
}
