package com.hal;

public class Test {
	public static void main(String[] args) {
		Dao ud = UserDao.getInstance();
		System.out.println(ud instanceof Dao);
		Dao cd = CarDao.getInstance();
		System.out.println(cd instanceof Dao);

	}

}
