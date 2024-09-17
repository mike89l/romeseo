package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class ProjectConfig extends BaseEntity {

    /** 状态参数 */
    @Excel(name = "关闭")
    private static int close = 0;

    /** 状态参数 */
    @Excel(name = "开启")
    private static int open = 1;



    public static int getOpen() {
        return open;
    }

    public static void setOpen(int open) {
        ProjectConfig.open = open;
    }

    public static int getClose() {
        return close;
    }

    public static void setClose(int close) {
        ProjectConfig.close = close;
    }



}
