package com.hsy.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.hsy.common.utils.ObjectUtils;
import com.hsy.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 张梓枫
 * @date 2019年3月29日下午3:26:36
 * @Description
 */
@Slf4j
public class RequestUtils {
    public static String parseRequest(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        ServletInputStream inputStream = request.getInputStream();
        Reader read = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(read);
        try {
            StringBuilder body = new StringBuilder();
            while (true) {
                String info = reader.readLine();
                if (ObjectUtils.isEmpty(info)) {
                    break;
                }
                body.append(info);
            }
            return body.toString();
        } catch (Exception e) {
            e.printStackTrace();
			close(read, reader);
        } finally {
			close(read, reader);
        }
        return null;
    }

	private static void close(Reader read, BufferedReader reader) throws IOException {
		if (read != null) {
			read.close();
		}
		if (reader != null) {
			reader.close();
		}
	}

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        log.info("获取到的客户端ip地址：{}", ip);
        if (ObjectUtils.isNotEmpty(ip)){
            if (StringUtils.contains(ip, ",")) {
                return ip.substring(0, ip.indexOf(","));
            }
            return ip;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
       
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
