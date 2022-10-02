package base;

import org.apache.log4j.Logger;



public class Example {
	static Logger log = Logger.getLogger(Example.class.getName()); 
	
	public static void main(String[] args) {
		
		log.debug("Hello debug");
		log.info("hii info mesg");
		
	}
}
