package com.dlh.generic.enumtest;

public enum CustomerType implements EnumItem.IEnumItem {

	Resident_user(1, "居民用户"), 
	Business_user(2, "商业用户"),
	GY_user(4, "工业用户");
	private int	value;
	private String	desc;

	private CustomerType(int value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public static String getDesc(int value) {
		for (CustomerType ct : CustomerType.values()) {
			if (ct.getValue() == value) {
				return ct.desc;
			}
		}
		return "未知";
	}
	
	public static CustomerType getObj(int value) {
		for (CustomerType ct : CustomerType.values()) {
			if (ct.getValue() == value) {
				return ct;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
