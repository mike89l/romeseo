package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class PjtConfig extends BaseEntity {

    /** 状态参数 */
    @Excel(name = "关闭")
    private static int close = 0;

    /** 状态参数 */
    @Excel(name = "开启")
    private static int open = 1;

    /** 系统时间 */
    @Excel(name = "系统时间")
    private static Date date = new Date();

    public static int getClose() {
        return close;
    }

    public static void setClose(int close) {
        PjtConfig.close = close;
    }

    public static int getOpen() {
        return open;
    }

    public static void setOpen(int open) {
        PjtConfig.open = open;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        PjtConfig.date = date;
    }
}
