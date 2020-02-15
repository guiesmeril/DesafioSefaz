package control.facade;

import java.util.List;

import control.users.UsersControl;
import model.users.Usuario;
import model.users.UsersInterface;

public class FacadeControl implements UsersInterface {

	private static UsersControl usuarioInstancia;

	FacadeControl() {
		usuarioInstancia = new UsersControl();

	}

	public static UsersControl getInstanciaUsuario() {
		if (usuarioInstancia == null) {
			usuarioInstancia = new UsersControl();
		}
		return usuarioInstancia;
	}

	@Override
	public void registerUsers(Usuario u) {
		usuarioInstancia.registerUsers(u);

	}

	@Override
	public Usuario loginAcessUsers(String email, String senha) {
		return usuarioInstancia.loginAcessUsers(email, senha);
	}

	@Override
	public void deleteUsers(int idUsuario) {
		usuarioInstancia.deleteUsers(idUsuario);
	}

	@Override
	public void updateUsers(Usuario u) {
		usuarioInstancia.updateUsers(u);
	}

	@Override
	public List<Usuario> listUsers() {
		return usuarioInstancia.listUsers();
	}

	@Override
	public Usuario checkExistenceEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioInstancia.checkExistenceEmail(email);
	}

}
