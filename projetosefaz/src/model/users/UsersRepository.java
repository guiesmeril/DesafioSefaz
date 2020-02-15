package model.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entityManagerJPA.EntityManagerUtil;

public class UsersRepository {
	EntityManager em = EntityManagerUtil.getEntityManager();

	public void registerUsers(Usuario u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();

	}
	public Usuario checkExistenceEmail(String email) {
		try {
		Usuario usuario = (Usuario) em
				.createQuery("SELECT usuario from Usuario usuario where usuario.email= \r\n"
						+ "	             :email")
				.setParameter("email", email).getSingleResult();
		return usuario ;
		}catch (NoResultException e) {
			return null ;
		}
	}
		

	public void deleteUsers(int idUsuario) {
		Usuario usuario = em.find(Usuario.class, idUsuario);
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
	}

	public void updateUsers(Usuario u) {
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
	}

	public Usuario loginAcessUsers(String email, String senha) {
		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT usuario from Usuario usuario where usuario.email= \r\n"
							+ "	             :email and usuario.senha = :senha")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public List<Usuario> listUsers() {
		String consulta = "From Usuario ORDER BY id DESC";
		TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
		List<Usuario> resultado = query.getResultList();

		return resultado;
	}
}

