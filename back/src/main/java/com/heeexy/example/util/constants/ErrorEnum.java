package com.heeexy.example.util.constants;

/**
 * @author: hxy
 * @date: 2017/10/24 10:16
 */
public enum ErrorEnum {
	/*
	 * 错误信息
	 * */
	E_400("400", "请求处理异常，请稍后再试"),
	E_405("405", "系统错误，请联系管理员"),
	E_500("500", "请求方式有误,请检查 GET/POST"),
	E_501("501", "请求路径不存在"),
	E_502("502", "权限不足"),
	E_10008("10008", "角色删除失败,尚有用户属于此角色"),
	E_10009("10009", "账户已存在"),
	E_30001("30001","添加失败，数据超出限制"),
	E_20011("20011", "登陆已过期,请重新登陆"),
	E_90003("90003", "缺少必填参数"),
	/**
	 * 小程序错误
	 */
	WX_807("WX_807","参数输入有误"),
	WX_803("WX_803","数据已存在" ),
	WX_601("WX_601","帖子不存在"),
	WX_900("WX_900","分页参数错误"),
	/**
	 * 举报相关错误定义
	 */
	WX_950("WX_950","举报类型错误"),
	WX_951("WX_951","已举报过该帖子"),
	WX_952("WX_952","今日举报次数已用完"),
	WX_953("WX_953","举报帖子不存在"),
	WX_884("WX_886","用户已被禁言"),
	WX_885("WX_886","该用户未禁言"),
	WX_886("WX_886","该用户已被限制，更改限制时长请先解除限制状态");

	private String errorCode;

	ErrorEnum(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private String errorMsg;

	ErrorEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}


}