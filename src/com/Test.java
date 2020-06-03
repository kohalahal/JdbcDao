package com;

import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		//준비
	    List<User> list;
	    UserDao userDao = UserDao.getInstance();
		userDao.deleteAllUser();
		User u1 = new User("121","1sdsd34","aaa","d@d.com");
		User u2 = new User("bb","1234","김a김김","d@d.com");
		User u3 = new User("vv","1234","김s김","d@d.com");
		User u4 = new User("add","1234","김f김김","d@d.com");
		User u5 = new User("ss","1234","김d김","d@d.com");
		
		
		
		
//		UserDao userDao = UserDao.getInstance();
//		System.out.println(UserDao.instance.equals(userDao));
		
		
//	    List<User> list;
////	    System.out.println(list);
//	    
////	    userDao.insertUser(new User("aaa", "wewe", "ㅋㅋㅋ", "d@d.com"));
////	    list = userDao.selectAllUsers();
////	    System.out.println(list);
//   
////	    System.out.println(userDao.selectUser("ff22f"));
////	    userDao.deleteUser("ff22f");
////	    System.out.println(userDao.selectUser("ff22f"));
//	    
//	    userDao.deleteAllUser();
//	    list = userDao.selectAllUsers();
//	    System.out.println(list);
////	
////		롤백,커밋 테스트
//		System.out.println("0:"+userDao.countAllUsers());
//		userDao.setAutoCommit(false);
//		userDao.insertUser(u1);
//		userDao.insertUser(u2);
////		userDao.insertUser(u3);
////		userDao.insertUser(u4);
//		System.out.println("1:"+userDao.countAllUsers());
//		//userDao.commit();\
//		userDao.rollback();
//		System.out.println("2:"+userDao.countAllUsers());
////		userDao.closeQuery();
////		System.out.println("3:"+userDao.countAllUsers());
////		userDao.closeConn();
//		userDao.closeAll();
		//	    userDao.setConn();
//	    userDao.setAutoCommit(false);
//	    userDao.insertUser(new User("121","1sdsd34","aaa","d@d.com"));
//	    userDao.rollback();
////	    userDao.commit();
//	    userDao.rollback();
////    userDao.commit();
////	    userDao.insertUser(new User("bb","1234","김a김김","d@d.com"));
////	    userDao.insertUser(new User("vv","1234","김s김","d@d.com"));
////	    userDao.insertUser(new User("add","1234","김f김김","d@d.com"));
////	    userDao.insertUser(new User("ss","1234","김d김","d@d.com"));
////	    
//	    list = userDao.selectAllUsers();
//	    System.out.println(list);
	    
	}

}
