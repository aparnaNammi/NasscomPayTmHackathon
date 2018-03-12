package com.lsa.util;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
public class LSLogger{

   /* Get actual class name to be printed on */
   final static Logger logger = Logger.getLogger(LSLogger.class.getName());
   
   public static void main(String[] args)throws IOException,SQLException{
	   logger.debug("Sample debug message");
	   logger.info("Sample info message");
   }
}
