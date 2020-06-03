package com.hal;

import java.util.List;

public class CarDao extends Dao<Car>{
	private static CarDao instance = new CarDao();

	public static Dao<Car> getInstance() {
		return (Dao<Car>) instance;
	}
	@Override
	public int insertData(Car t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateData(Car t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car deleteData(Car t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car selectData(Car t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTableName(String tableName) {
		// TODO Auto-generated method stub
		
	}

}
