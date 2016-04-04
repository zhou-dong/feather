package org.feather.implement.proxy;

import java.lang.reflect.Proxy;

public class TestProxy {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException {
		Person liangKai = new LiangKaiProxy(new LiangKai());
		System.out.println(liangKai.getHeight());
		Class<LiangKai> class1 = (Class<LiangKai>) Class
				.forName("org.feather.implement.proxy.LiangKai");
		Person liangKai2 = null;
		try {
			liangKai2 = class1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Person person = (Person) Proxy.newProxyInstance(liangKai2.getClass().getClassLoader(),
				new Class<?>[] { Person.class }, new LiangKaiInvocationHandler(liangKai2));
		System.out.println(person.getName());

		test();
	}

	public static void test() {
		System.out.println();
		Person p1 = new LiangKai();

		String name = "getName";
		if (name.equals("getName")) {
			p1.getName();
		} else if (name.equals("getHeight")) {
			p1.getHeight();
		}
		System.out.println(p1.getName());
	}

}
