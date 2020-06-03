package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserDaoTest {
	UserDao ud;
	User u1;
	User u1_1;	
	User u2;
	User u2_1;
	User u3;
	User u3_1;
	User u4;

	Dao d;
	
	void ready() {
		ud = UserDao.getInstance();
		ud.deleteAllUser();
		u1 = new User("user1","aaaa","박나래","asd@dd.com");
		u1_1 = new User("user1","bbbb","박나래","asd@dd.com");	
		u2 = new User("user2","1234","김유자","d@d.com");
		u2_1 = new User("user2","5555","김유자","d@aaa.com");
		u3 = new User("vv","1234","김s김","d@d.com");
		u3_1 = new User("vv","1234","김s김","d@d.com");
		u4 = new User("add","1234","김f김김","d@d.com");

		d = Dao.getInstance();
	}
	
	
	
	/* 싱글턴 */
	@Test
	void testGetInstance() {
		ready();
		assertEquals(ud, UserDao.getInstance());
		ud.deleteAllUser();
	}

	/* 추가,삭제,카운트 테스트 */
	@Test
	void testDeleteUser() {
		ready();
		/* 보통 상태에서 지워지는지 확인 */
		int before = ud.countAllUsers();
		ud.insertUser(u1);
		assertEquals(before+1, ud.countAllUsers());
		ud.deleteUser(u1);
		assertEquals(before, ud.countAllUsers());
		/* 지운회원 못찾기 */
		try {
			assertNull(ud.selectUser(u1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* 없는사람 지우려할떄 */
		assertTrue(ud.deleteUser(u4)==0);
	}

	/* 추가한 유저 아이디로 찾기, 유저 이퀄즈테스트 */
	@Test
	void testSelectUser() {
		ud.insertUser(u1);
		try {
			assertEquals(u1, ud.selectUser(u1.getUser_Id()));
			assertNull(ud.selectUser(u2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* 없으면 못찾기 */
		
	}
	
	/* 업데이트하면 이전 아이디로 찾아도 업데이트한유저나오기 */
	@Test
	void testUpdateUser() {
		ud.insertUser(u1);
		ud.updateUser(u1_1);
		// 기존에 있는 데이터 업데이트
		assertTrue(ud.updateUser(u1_1)==1);

		// 기존에 없는 데이터 업데이트
		assertTrue(ud.updateUser(u1_1)==0);

		try {
			assertEquals(u1_1, ud.selectUser(u1.getUser_Id()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testDeleteAllUser() {
		/* 유저 추가하면 0아님 */
		ud.insertUser(u3);
		ud.insertUser(u4);
		assertNotEquals(ud.countAllUsers(), 0);
		/* 다 지우면 유저 0명 */
		ud.deleteAllUser();
		assertEquals(ud.countAllUsers(), 0);
	}
	
	/* 다오 싱글턴테스트 */
	@Test
	void testGetInstance1() {
		assertEquals(d, Dao.getInstance());
	}
	
	/* 다오의 테이블 겟세터 유저다오에서 테스트 */
	@Test
	void testGetTableName() {
		assertEquals(ud.tableName, ud.getTableName());
	}

	@Test
	void testSetTableName() {
		String newName = "ㅋㅋ";
		ud.setTableName(newName);
		assertEquals(ud.getTableName(), newName);
	}

}
