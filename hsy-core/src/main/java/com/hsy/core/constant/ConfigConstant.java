package com.hsy.core.constant;

/**
 * @author 张梓枫
 * @Description 系统静态变量类
 * @date:   2019年1月2日 上午9:44:34
 */
public final class ConfigConstant {

	public static final String SCAN_BASE_PACKAGES = "com.hsy";
    
    public final static String PATH = "/**";
    
    public static final String IMAGE_BASE64_DATA = "data:image/";
    
    public static final String IMAGE_BASE64 = ";base64,";

    public static final String TOKEN_DESCRIPTION = "用户认证token";
    
    public static final String API_TITLE = "api";
    
    public static final String API_VERSIPN = "v01";
    
	public static final String API_PACKAGE = "com.hsy";
    
    public static final String CHARACTER_SET = "UTF-8";
    
    /**
     * 树结构最顶层节点的父节点
     */
    public static final String TOP_PARENT_ID = "00000-00000-00000-00000";
    
    /**
     * 校验字符串不能出现敏感字符
     */
    public static final String REGEX = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    
}
