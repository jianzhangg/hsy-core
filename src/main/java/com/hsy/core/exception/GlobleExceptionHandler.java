package com.hsy.core.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hsy.common.exception.BLogicException;
import com.hsy.core.bean.ResultInfo;
import com.hsy.core.utils.ResultUtils;
import com.hsy.resource.enums.ResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 张梓枫
 * @Description 自定义异常处理
 * @date: 2019年1月3日 下午4:41:07
 */
@Slf4j
@RestControllerAdvice
public class GlobleExceptionHandler {

	/**
	 * @author 张梓枫
	 * @param request
	 * @param e
	 * @return
	 * @return ResponseEntity<ResultInfo<String>>
	 * @throws Exception
	 * @description 系统错误异常处理
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResultInfo<String>> exceptionHandler(Exception e) {
		e.printStackTrace();
		return this.getExceptionMsgs(ResultCode.SERVER_ERROR, e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	/**
	 * @author 张梓枫
	 * @param exception
	 * @return
	 * @return ResponseEntity<ResultInfo<String>>
	 * @throws Exception
	 * @description 数据校验异常处理
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ResultInfo<String>> validExceptionHandler(MethodArgumentNotValidException exception) {
		exception.printStackTrace();
		BindingResult bindingResult = exception.getBindingResult();// 捕获的所有错误对象
		List<ObjectError> errors = bindingResult.getAllErrors();
		ObjectError error = errors.get(0);
		String message = error.getDefaultMessage();
		return this.getExceptionMsgs(ResultCode.BUSSINESS_SERVER_ERROR, message, HttpStatus.OK);
	}

	/**
	 * @author 张梓枫
	 * @param bLogicException
	 * @return
	 * @return ResponseEntity<ResultInfo<String>>
	 * @throws Exception
	 * @description 业务异常处理
	 */
	@ExceptionHandler(value = BLogicException.class)
	public ResponseEntity<ResultInfo<String>> blogicExceptionHandler(BLogicException bLogicException) {
		bLogicException.printStackTrace();
		String message = bLogicException.getLocalizedMessage();
		return this.getExceptionMsgs(ResultCode.BUSSINESS_SERVER_ERROR, message, HttpStatus.OK);
	}

	private ResponseEntity<ResultInfo<String>> getExceptionMsgs(ResultCode code, String message, HttpStatus httpStatus) {
		log.error(message);
		return new ResponseEntity<>(ResultUtils.createRet(message, code), httpStatus);
	}
}
