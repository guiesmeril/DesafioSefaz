package entityManagerJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
// Singleton 
	private static EntityManagerFactory enf = null;
	private static EntityManager em = null ;
	
	public static EntityManager getEntityManager() {
		
		
		if(enf == null ) {
		   enf = Persistence.createEntityManagerFactory("Guilherme"); // inicia o vínculo com o banco de dados
			
		}if(em == null) {
			em = enf.createEntityManager(); // Objeto consultado ou persistido
			

		}
		return em;
	}
		
		
}
 