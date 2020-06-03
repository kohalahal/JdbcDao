package com;

public class dddd {
	
	public static void main(String[] args) {
		Water w = new Water();
		System.out.println(w.ALCOHOL_CONTENT);
		System.out.println(Vodka.ALCOHOL_CONTENT);
		Water.display();
		Vodka.display();
	}
	Integer.parseInt(request.getParameter("code")==null?"0":request.getParameter("code"))>0? true:false;
}

class Water{
	public static float ALCOHOL_CONTENT = 0f;
	static void display() {
		System.out.println("water");
	}
	protected void display2() {
		
	}
}
 
class Vodka extends Water{
	public static float ALCOHOL_CONTENT = 0.4f;
	static void display() {
		System.out.println("vodka");
	}
	
	private void display2() {
		
	}
}