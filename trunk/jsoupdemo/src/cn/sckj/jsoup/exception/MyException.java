package cn.sckj.jsoup.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123456789L;
	public MyException(){}
	public MyException(String exceptionStr){
		System.out.println(exceptionStr);
	}
}
