package test;

public class RunPerformanceTest {

	public static void main(String[] args) {
		PerformanceTest test = new PerformanceTest();
		long first = test.test1();
		long second = test.test2();
		long third = test.test3();
		long fourth = test.test4();
		System.out.println("Loading takes:");
		System.out.println(first + "/n" + second + "/n" + third + "/n" + fourth);
		
		first = test.test1();
		second = test.test2();
		third = test.test3();
		fourth = test.test4();
		System.out.println("Loaded takes:");
		System.out.println(first + "/n" +  second + "/n" + third + "/n" + fourth);
	}	
}
