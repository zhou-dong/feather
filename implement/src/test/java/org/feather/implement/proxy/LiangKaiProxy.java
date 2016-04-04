package org.feather.implement.proxy;

public class LiangKaiProxy implements Person {

	LiangKai liangKai = null;

	public LiangKaiProxy(LiangKai liangKai) {
		this.liangKai = liangKai;
	}

	public int getHeight() {
		return liangKai.getHeight() + 100;
	}

	public String getName() {
		return liangKai.getName();
	}

	public int getWeight() {
		return liangKai.getWeight();
	}

}
