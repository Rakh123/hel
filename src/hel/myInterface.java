package hel;

public interface myInterface {
	
	public void m1();

	
	 default void m2() { System.out.println("hey ..!");};
	 

	
	
	public static void myMethod() {
		System.out.println("static method in interface...");
	}
	
	

}
