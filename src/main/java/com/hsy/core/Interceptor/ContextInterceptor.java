package com.hsy.core.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsy.common.bean.PageInfo;
import com.hsy.common.bean.User;
import com.hsy.common.constant.CommonConstant;
import com.hsy.common.context.ContextHolder;
import com.hsy.common.jwt.JwtSecurityUtils;
import com.hsy.common.utils.ObjectUtils;

import io.jsonwebtoken.Claims;

/**
 * @author 张梓枫
 * @date  2019年3月21日下午4:43:59
 * @Description
 */
public class ContextInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		String token = request.getHeader(CommonConstant.SESSION_TOKEN);
		if (ObjectUtils.isEmpty(token)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		Claims claims = JwtSecurityUtils.parseToken(token);
		String uid = claims.getId();
		String username = claims.getSubject();
		String sellId = claims.getIssuer();
		this.setContetHolder(uid, username, sellId, request);
        return true;
    }
    
	private void setContetHolder(String uid, String username, String sellId, HttpServletRequest request) {
        User user = new User();
		user.setId(ObjectUtils.convertToInteger(uid));
        user.setUsername(username);
		user.setSellerId(ObjectUtils.convertToInteger(sellId));
        ContextHolder.setUser(user);
        PageInfo pageInfo = this.createPageInfo(request);
		ContextHolder.setPage(pageInfo);
    }
    
    private PageInfo createPageInfo(HttpServletRequest request) {
        Integer pageNum = ObjectUtils.convertToInteger(request.getHeader(CommonConstant.PAGE_NUM));
        Integer pageSize = ObjectUtils.convertToInteger(request.getHeader(CommonConstant.PAGE_SIZE));
        if (ObjectUtils.isEmpty(pageNum) || ObjectUtils.isEmpty(pageSize)) {
            return null;
        }
        String orderBy = request.getHeader(CommonConstant.ORDER_BY);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize,orderBy);
        return pageInfo;
    }
}
