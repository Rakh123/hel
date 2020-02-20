package hel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogPractice {
	final static Logger log= Logger.getLogger(LogPractice.class);
	 
	public static void main(String[] args) {
		
		PatternLayout layout = new PatternLayout();
	     String conversionPattern = " %d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";
	     layout.setConversionPattern(conversionPattern);

	     //  console appender
	     ConsoleAppender consoleAppender = new ConsoleAppender();
	     consoleAppender.setLayout(layout);
	     consoleAppender.activateOptions();

	     //  file appender
	     FileAppender fileAppender = new FileAppender();
	     fileAppender.setFile("/home/nageswararao/Documents/applog3.txt");
	     fileAppender.setLayout(layout);
	     fileAppender.activateOptions();

	     // configures the root logger
	     Logger rootLogger = Logger.getRootLogger();
	     rootLogger.setLevel(Level.DEBUG);
	     rootLogger.addAppender(consoleAppender);
	     rootLogger.addAppender(fileAppender);
		
		BasicConfigurator.configure();
		log.debug("this is a debug log message");
	
		log.warn("this is a warning log message");
	
		try {
			String str[]= { "hey","hi"};
			String s= str[10];
			if (log.isDebugEnabled()) {
				log.debug("insertIntoActivityLog" + str[10]);
				
			}
			log.info("this is a information log message"+s);
		} catch (Exception e) {
			log.error("the proplem is"+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
	