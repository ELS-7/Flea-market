package com.xenus.sts.vo;

//VO
public class Returns {
	private Integer code;
	private String  text;
	private String  keyCode;
	private String  keyStr;
	
	public Integer getCode() {
		return code;
	}
	public Returns setCode(Integer code) {
		this.code = code;
		return this;
	}
	public String getText() {
		return text;
	}
	public Returns setText(String text) {
		this.text = text;
		return this;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public Returns setKeyCode(String keyCode) {
		this.keyCode = keyCode;
		return this;
	}
	public String getKeyStr() {
		return keyStr;
	}
	public Returns setKeyStr(String keyStr) {
		this.keyStr = keyStr;
		return this;
	}
}