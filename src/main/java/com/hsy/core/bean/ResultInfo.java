package com.hsy.core.bean;

import com.hsy.resource.enums.ResultCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 张梓枫
 * @Description
 * @date:   2019年1月3日 下午2:18:29
 */
@Data
@ApiModel(description = "返回参数")
public class ResultInfo<T> {

	@ApiModelProperty(value = "消息提示", dataType = "String")
    private String msg;

	@ApiModelProperty(value = "返回编码", dataType = "String")
	private String code;

	@ApiModelProperty(value = "返回数据", dataType = "Object")
    private T datas;

	@ApiModelProperty(value = "分页数据总条数", dataType = "long")
	private Long total;

	@ApiModelProperty(value = "分页当前页数", dataType = "int")
	private Integer pageNum;

	@ApiModelProperty(value = "分页显示条数", dataType = "int")
	private Integer pageSize;

	@ApiModelProperty(value = "分页总页数", dataType = "int")
	private Integer pageTotals;

    public ResultInfo() {
		this(null, null, null);
    }
    
	public ResultInfo(ResultCode code) {
		this(null, code, null);
	}

	public ResultInfo(String msg, ResultCode code) {
		this(msg, code, null);
	}

	public ResultInfo(ResultCode code, T datas) {
		this(null, code, datas);
	}

	public ResultInfo(String msg, ResultCode code, T datas) {
		this.msg = msg;
		this.code = code.getCode();
		this.datas = datas;
	}
}
