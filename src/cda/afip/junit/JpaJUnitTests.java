package cda.afip.junit;

import org.apache.log4j.Logger;
import org.junit.Test;

public class JpaJUnitTests {
	private static Logger logger = Logger.getLogger(JpaJUnitTests.class);
	@Test
	public void test() {
		
		
//		try {
//	        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cda_jee_jpa");
//	        EntityManager em = entityManagerFactory.createEntityManager();          
//	    }catch(Exception e) {
//	    	logger.info("Error (BD) : "+e);
//	    }
		
//		Dao<User> dao = new Dao<User>(User.class);
//		//List<User> users = dao.getAll();
//		//users.forEach(System.out::println);
//		
//		
//		List<User> users = dao.getFiltred("SELECT * FROM users WHERE login='donut' AND password='mmm'");
//		System.out.println("******************");
//		users.forEach(x->logger.info("x "+x));
//		System.out.println("******************");
		
//		List result = em.createNativeQuery("SELECT * FROM users a WHERE a.login='donut'").getResultList();
//		result.forEach(x->System.out.println(x));
	}

}
