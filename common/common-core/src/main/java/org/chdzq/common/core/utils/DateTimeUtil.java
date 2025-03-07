package org.chdzq.common.core.utils;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Locale;
import java.util.Objects;

/**
 * 时间工具类
 *
 * @author chdzq
 * @create 2025/2/19 22:42
 */
public class DateTimeUtil {
    /**
     * 解析日期时间字符串为{@link LocalDateTime}，格式:yyyy-MM-dd HH:mm:ss<br>
     *
     * @param text      日期时间字符串
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text) {
        return parse(text, NORM_DATETIME_FORMATTER);
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，格式支持日期时间、日期、时间<br>
     * 如果formatter为{code null}，则使用{@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}
     *
     * @param text      日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        if (!StringUtils.hasText(text)) {
            return null;
        }
        if (Objects.nonNull(formatter)) {
            return of(formatter.parse(text));
        }
        return LocalDateTime.parse(text);
    }

    private static LocalDateTime of(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        } else if (temporalAccessor instanceof LocalDate) {
            return ((LocalDate)temporalAccessor).atStartOfDay();
        } else {
            return temporalAccessor instanceof Instant ?
                    LocalDateTime.ofInstant((Instant)temporalAccessor, ZoneId.systemDefault()) :
                    LocalDateTime.of(
                            get(temporalAccessor, ChronoField.YEAR),
                            get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                            get(temporalAccessor, ChronoField.DAY_OF_MONTH),
                            get(temporalAccessor, ChronoField.HOUR_OF_DAY),
                            get(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
                            get(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
                            get(temporalAccessor, ChronoField.NANO_OF_SECOND));
        }
    }

    private static int get(TemporalAccessor temporalAccessor, TemporalField field) {
        return temporalAccessor.isSupported(field) ? temporalAccessor.get(field) : (int)field.range().getMinimum();
    }

    /**
     * 解析日期时间字符串为{@link LocalDate}，格式:yyyy-MM-dd
     *
     * @param text      日期时间字符串
     * @return {@link LocalDate}
     */
    public static LocalDate parseDate(CharSequence text) {
        return parseDate(text, NORM_DATE_FORMATTER);
    }

    /**
     * 解析日期时间字符串为{@link LocalDate}，格式支持日期
     *
     * @param text      日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return {@link LocalDate}
     */
    public static LocalDate parseDate(CharSequence text, DateTimeFormatter formatter) {
        if (!StringUtils.hasText(text)) {
            return null;
        }
        if (Objects.nonNull(formatter)) {
            return ofDate(formatter.parse(text));
        }
        return LocalDate.parse(text);
    }

    public static LocalDate ofDate(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        if (temporalAccessor instanceof LocalDateTime) {
            return ((LocalDateTime) temporalAccessor).toLocalDate();
        } else if(temporalAccessor instanceof Instant){
            return of(temporalAccessor).toLocalDate();
        }

        return LocalDate.of(
                get(temporalAccessor, ChronoField.YEAR),
                get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                get(temporalAccessor, ChronoField.DAY_OF_MONTH)
        );
    }

    /**
     * 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     *
     * @param time {@link LocalDateTime}
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime time) {
        return format(time, NORM_DATETIME_FORMATTER);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param time      {@link LocalDateTime}
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return _format(time, formatter);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param date      {@link LocalDate}
     * @param formatter 日期格式化器
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime date, String formatter) {
        return _format(date, StringUtil.isBlank(formatter) ? NORM_DATE_FORMATTER : DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * 格式化日期时间为yyyy-MM-dd格式
     *
     * @param date {@link LocalDate}
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date) {
        return format(date, NORM_DATE_FORMATTER);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param date      {@link LocalDate}
     * @param formatter 日期格式化器
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return _format(date, formatter);
    }

    /**
     * 格式化日期时间为指定格式<br>
     * 如果为{@link Month}，调用{@link Month#toString()}
     *
     * @param time      {@link TemporalAccessor}
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return 格式化后的字符串
     */
    private static String _format(TemporalAccessor time, DateTimeFormatter formatter) {
        if (null == time) {
            return null;
        }

        if(time instanceof Month){
            return time.toString();
        }

        if(null == formatter){
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        }

        try {
            return formatter.format(time);
        } catch (UnsupportedTemporalTypeException e){
            if(time instanceof LocalDate && e.getMessage().contains("HourOfDay")){
                // 用户传入LocalDate，但是要求格式化带有时间部分，转换为LocalDateTime重试
                return formatter.format(((LocalDate) time).atStartOfDay());
            }else if(time instanceof LocalTime && e.getMessage().contains("YearOfEra")){
                // 用户传入LocalTime，但是要求格式化带有日期部分，转换为LocalDateTime重试
                return formatter.format(((LocalTime) time).atDate(LocalDate.now()));
            } else if(time instanceof Instant){
                // 时间戳没有时区信息，赋予默认时区
                return formatter.format(((Instant) time).atZone(ZoneId.systemDefault()));
            }
            throw e;
        }
    }

    private DateTimeUtil() {}

    public static final DateTimeFormatter NORM_DATETIME_FORMATTER = createFormatter("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter NORM_DATE_FORMATTER = createFormatter("yyyy-MM-dd");

    public static DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault());
    }
}
