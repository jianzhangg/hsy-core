package com.hsy.core.service;

import com.hsy.core.bean.ResultInfo;

/**
 * @author 张梓枫
 * @date 2019年4月29日下午1:59:27
 * @desc 业务逻辑接口类，每个业务逻辑必须实现此接口
 */
public interface IBlogic<P, R> {

	/**
	 * @author 张梓枫
	 * @param p 请求参数
	 * @return
	 * @throws Exception
	 * @return ResultInfo<R> 返回参数
	 * @throws Exception
	 * @desc 业务具体操作接口，在微服务中，为了方便维护，每个业务逻辑只有一个实现方法，便于每个业务独立解耦，互不干涉
	 */
	public ResultInfo<R> execute(P paramP) throws Exception;
}
