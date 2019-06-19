package com.hsy.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import com.hsy.common.utils.ObjectUtils;

/**
 * @author 张梓枫
 * @Description bean操作类
 * @date:   2019年1月3日 下午4:57:14
 */
public final class BeanUtils {
    
    private static Converter converter = new BeanConverter();

	public static <S, T> T copy(S s, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		BeanCopier copier = BeanCopier.create(s.getClass(), clazz, true);
		T t = clazz.newInstance();
		copier.copy(s, t, converter);
		return t;
    }
    
	/**
	 * @author 张梓枫
	 * @param <S>
	 * @param <T>
	 * @param sList
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @return List<T>
	 * @throws Exception
	 * @description 2019-5-22最新修改，该方法现在无法复制mybatis的page对象，如果需要复制page对象信息，请使用PageBeanUtils类
	 */
	public static <S, T> List<T> copy(List<S> sList, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return copyList(sList, clazz);
    }
    
	private static <S, T> List<T> copyList(List<S> sList, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		List<T> tList = new ArrayList<T>();
        if (ObjectUtils.isEmpty(sList)) {
			return tList;
        }
        BeanCopier copier = BeanCopier.create(sList.get(0).getClass(), clazz, true);
        for (S s : sList) {
            T t = clazz.newInstance();
            copier.copy(s, t, converter);
            tList.add(t);
        }
        return tList;
    }
}
