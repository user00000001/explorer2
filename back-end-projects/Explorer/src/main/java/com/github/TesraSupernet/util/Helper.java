/*
 * Copyright (C) 2018 The TesraSupernet Authors
 * This file is part of The TesraSupernet library.
 *
 * The TesraSupernet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The TesraSupernet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The TesraSupernet.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.github.TesraSupernet.util;

import com.github.TesraSupernet.mapper.BlockMapper;
import com.github.TesraSupernet.model.common.TstIdEventEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author zhouq
 * @date 2018/2/27
 */
public class Helper {

    private static final String SEPARATOR = "\\|\\|";


    /**
     * check param whether is null or ''
     *
     * @param params
     * @return boolean
     */
    public static Boolean isEmptyOrNull(Object... params) {
        if (params != null) {
            for (Object val : params) {
                if ("".equals(val) || val == null) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    public static Boolean isNotEmptyAndNull(Object... params) {
        return !isEmptyOrNull(params);
    }


    /**
     * 判断时间范围是否超过一个月
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Boolean isTimeRangeExceedLimit(Long beginTime, Long endTime) {

        if ((endTime - beginTime) > (ConstantParam.REQTIME_MAX_RANGE)) {
            return true;
        }
        return false;
    }


    /**
     * 判断时间范围是否超过一周
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Boolean isTimeRangeExceedWeek(Long beginTime, Long endTime) {

        if ((endTime - beginTime) > (ConstantParam.REQTIME_MAX_RANGE_WEEK)) {
            return true;
        }
        return false;
    }


    /**
     * format tstId operation description
     *
     * @param inputStr
     * @return
     */
    public static String templateTstIdOperation(String inputStr) {

        StringBuffer descriptionSb = new StringBuffer();

        String[] desArray = inputStr.split(SEPARATOR);
        String action = desArray[0];
        if (TstIdEventEnum.REGISTERTSTID.value().equals(action)) {
            descriptionSb.append("register TstId");
        } else if (TstIdEventEnum.PUBLICKEYOPE.value().equals(action)) {
            descriptionSb.append(desArray[1]);
            descriptionSb.append(" publicKey:");
            descriptionSb.append(desArray[3]);
        } else if (TstIdEventEnum.ATTRIBUTEOPE.value().equals(action)) {
            descriptionSb.append(desArray[1]);
            descriptionSb.append(" attribute:");
            String attrName = desArray[3];
            if (attrName.startsWith(ConstantParam.CLAIM)) {
                descriptionSb.append("claim");
            } else {
                descriptionSb.append(desArray[3]);
            }
        } else if (TstIdEventEnum.RECOVERYOPE.value().equals(action)) {
            descriptionSb.append(desArray[1]);
            descriptionSb.append(" recovery:");
            descriptionSb.append(desArray[3]);
        } else {
            descriptionSb.append(action);
        }

        return descriptionSb.toString();
    }

    /**
     * 判断redis的key是否属于REDIS_LONGEXPIRETIME_KEYLIST
     * @param redisKey
     * @return
     */
    public static Boolean isBelongRedisLongExpireMapper(String redisKey) {

        String packageName = BlockMapper.class.getPackage().getName();
        int index = redisKey.indexOf(packageName);
        if (index > 0) {
            String str = redisKey.substring(index + packageName.length() + 1, redisKey.length());
            int dex = str.indexOf(":");
            String mapperName = str.substring(0, dex);
            if (ConstantParam.REDIS_LONGEXPIRETIME_KEYLIST.contains(mapperName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断redis的key是否属于REDIS_MEDIUMEXPIRETIME_KEYLIST
     * @param redisKey
     * @return
     */
    public static Boolean isBelongRedisMediumExpireMapper(String redisKey) {

        String packageName = BlockMapper.class.getPackage().getName();
        int index = redisKey.indexOf(packageName);
        if (index > 0) {
            String str = redisKey.substring(index + packageName.length() + 1, redisKey.length());
            int dex = str.indexOf(":");
            String mapperName = str.substring(0, dex);
            if (ConstantParam.REDIS_MEDIUMEXPIRETIME_KEYLIST.contains(mapperName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取真实请求ip
     *
     * @param request
     * @return
     */
    public static String getHttpReqRealIp(HttpServletRequest request) {

        String ip = "";
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }

        if (ip.contains(":")) {
            int index = ip.indexOf(":");
            ip = ip.substring(0, index);
        }
        return ip;
    }

    public static String currentMethod() {
        return new Exception("").getStackTrace()[1].getMethodName();
    }



    /**
     * 根据base64后的图片数据生成本地文件
     *
     * @param imgStr
     * @param path
     * @return
     */
    public static File generateImage(String imgStr, String path) throws Exception {
        // 解密
        byte[] b = Base64.getDecoder().decode(imgStr);
        //处理数据
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OutputStream out = new FileOutputStream(path);
        out.write(b);
        out.flush();
        out.close();
        File file = new File(path);
        return file;
    }

}
