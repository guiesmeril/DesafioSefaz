package model.users;

import java.util.List;

public interface UsersInterface {
	public void registerUsers(Usuario u);

	public Usuario loginAcessUsers(String email, String senha);

	public void deleteUsers(int idUsuario);

	public void updateUsers(Usuario u);

	public List<Usuario> listUsers();
	
	public Usuario checkExistenceEmail(String email);

}
