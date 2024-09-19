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
}
