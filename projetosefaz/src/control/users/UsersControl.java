package control.users;

import java.util.List;

import model.users.Usuario;
import model.users.UsersInterface;
import model.users.UsersRepository;

public class UsersControl implements UsersInterface {

	private static UsersRepository repositorio;

	public UsersControl() {
		repositorio = new UsersRepository();
	}

	@Override
	public void registerUsers(Usuario u) {
		repositorio.registerUsers(u);
	}

	@Override
	public Usuario loginAcessUsers(String email, String senha) {
		return repositorio.loginAcessUsers(email, senha);
	}
		

	
	@Override
	public void deleteUsers(int idUsuario) {
		repositorio.deleteUsers(idUsuario);
	}
	
	@Override
	public void updateUsers(Usuario u) {
		repositorio.updateUsers(u);
	}

	@Override
	public List <Usuario> listUsers () {
		return repositorio.listUsers();
	}
	
	public Usuario checkExistenceEmail(String email) {
		return repositorio.checkExistenceEmail(email);
	}

}
