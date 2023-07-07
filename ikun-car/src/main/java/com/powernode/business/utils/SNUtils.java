package com.powernode.business.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author 徐联理
 * @Date 2023/7/5 15:18
 * @Description
 */
public class SNUtils {
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
    static Random random=new Random();

    /**
     * 生成出租单号
     */
    public static String createRentSn(){
        //CZ +时间+四位随机数
        return "CZ_"+sdf.format(new Date())+"_"+(random.nextInt(9000)+1000);
    }

    public static String createCheckSn() {
        return "CK_"+sdf.format(new Date())+"_"+(random.nextInt(9000)+1000);

    }
}
