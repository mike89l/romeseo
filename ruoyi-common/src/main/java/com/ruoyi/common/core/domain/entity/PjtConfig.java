package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class PjtConfig extends BaseEntity {

    /** 状态参数 */
    @Excel(name = "状态参数-0")
    private static String zero = String.valueOf(0);

    /** 状态参数 */
    @Excel(name = "状态参数-1")
    private static String one = String.valueOf(1);

    /** 状态参数 */
    @Excel(name = "状态参数-2")
    private static String two = String.valueOf(2);

    /** 状态参数 */
    @Excel(name = "状态参数-3")
    private static String three = String.valueOf(3);

    /** 系统时间 */
    @Excel(name = "系统时间")
    private static Date date = new Date();




    /** 状态参数 */
    @Excel(name = "企业类型-4-普通企业")
    private static String four = String.valueOf(4);

    /** 状态参数 */
    @Excel(name = "企业类型-4-普通企业")
    private static String fourValue = "普通企业";

    /** 状态参数 */
    @Excel(name = "企业类型-5-自营站")
    private static String five = String.valueOf(5);

    /** 状态参数 */
    @Excel(name = "企业类型-5-自营站")
    private static String fiveValue = "自营站";

    /** 状态参数 */
    @Excel(name = "企业类型-6-代理商")
    private static String six = String.valueOf(6);

    /** 状态参数 */
    @Excel(name = "企业类型-6-代理商")
    private static String sixValue = "代理商";

    public static String getZero() {
        return zero;
    }

    public static void setZero(String zero) {
        PjtConfig.zero = zero;
    }

    public static String getOne() {
        return one;
    }

    public static void setOne(String one) {
        PjtConfig.one = one;
    }

    public static String getTwo() {
        return two;
    }

    public static void setTwo(String two) {
        PjtConfig.two = two;
    }

    public static String getThree() {
        return three;
    }

    public static void setThree(String three) {
        PjtConfig.three = three;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        PjtConfig.date = date;
    }

    public static String getFour() {
        return four;
    }

    public static void setFour(String four) {
        PjtConfig.four = four;
    }

    public static String getFourValue() {
        return fourValue;
    }

    public static void setFourValue(String fourValue) {
        PjtConfig.fourValue = fourValue;
    }

    public static String getFive() {
        return five;
    }

    public static void setFive(String five) {
        PjtConfig.five = five;
    }

    public static String getFiveValue() {
        return fiveValue;
    }

    public static void setFiveValue(String fiveValue) {
        PjtConfig.fiveValue = fiveValue;
    }

    public static String getSix() {
        return six;
    }

    public static void setSix(String six) {
        PjtConfig.six = six;
    }

    public static String getSixValue() {
        return sixValue;
    }

    public static void setSixValue(String sixValue) {
        PjtConfig.sixValue = sixValue;
    }
}
