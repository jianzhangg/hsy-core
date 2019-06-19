package com.hsy.core.utils;

import java.util.List;

import com.hsy.core.bean.ResultInfo;
import com.hsy.resource.enums.ResultCode;

/**
 * @author 张梓枫
 * @Description 接口返回类型工具类
 * @date:   2019年1月3日 下午2:25:16
 */
public class ResultUtils {

	/**
	 * @author 张梓枫
	 * @param <T>
	 * @param list
	 * @return
	 * @return ResultInfo<List<T>>
	 * @throws Exception
	 * @description 2019-05-22最新修改，将数据库操作脱离出去，如需要返回数据库分页信息，请使用PageResultUtils
	 */
    public static <T> ResultInfo<List<T>> createRet(List<T> list) {
		ResultInfo<List<T>> bean = new ResultInfo<>(ResultCode.SUCCESS);
        if (list == null) {
            return bean;
        }
        bean.setDatas(list);
        return bean;
    }
    
    public static <T> ResultInfo<T> createRet(T data) {
		ResultInfo<T> bean = new ResultInfo<T>(ResultCode.SUCCESS, data);
        return bean;
    }
    
    public static <T> ResultInfo<T> createRet(String message) {
		return createRet(message, ResultCode.SUCCESS);
    }

	public static <T> ResultInfo<T> createRet(String message, ResultCode code) {
		ResultInfo<T> bean = new ResultInfo<T>(message, code);
		return bean;
	}
}
