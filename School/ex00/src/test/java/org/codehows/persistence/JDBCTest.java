package org.codehows.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JDBCTest {
	
	@Test
	public void testConnection() throws Exception {
		Class clz = Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		
		// 만번 동안 DB연결을 하고 끊고 했을 때 커넥션 풀을 쓰는 것과 쓰지 않는 것의 차이 
//		long start = System.currentTimeMillis();
//		
//		for (int i =0; i<100; i++) {
//			
			Connection con =
					DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:XE",
							"book_ex", "book_ex");
			
			
			log.info(con);
			con.close(); //bad code 
			// finally로 클로주 해주는게 좋은 코드  
//		}
//		long end = System.currentTimeMillis();
//		log.info("-----------------------");
//		log.info("end - start");
		
	}
}
	
	
	
	
	
	/*
	 * static { try { Class.forName("oracle.jdbc.driver.OracleDriver");
	 * }catch(Exception e){ System.err.println(e.getMessage()); } }
	 * 
	 * @Test public void testConnection() { try (Connection con =
	 * DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:XE",
	 * "book_ex", "book_ex")){ log.info(con); }catch (Exception e) {
	 * fail(e.getMessage()); }
	 * 
	 * 
	 * }
	 */

