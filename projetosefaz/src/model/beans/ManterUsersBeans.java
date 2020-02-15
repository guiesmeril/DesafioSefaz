package model.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import control.facade.FacadeControl;
import control.users.SessionContext;
import model.users.Usuario;

// Registrar o Usuário.
@ManagedBean
public class ManterUsersBeans {

	@EJB
	private Usuario usuario;
	private List<Usuario> usuarios;
	private int idUsuarioSelecionado;
	private String nomeSelecionado;
	private String emailSelecionado;
	private String senhaSelecionado;
	private String numeroSelecionado;
	private String tipoSelecionado;
	private Date dataSelecionado;
	private int telefoneSelecionado;

	public int getIdUsuarioSelecionado() {
		return idUsuarioSelecionado;
	}
	public void setIdUsuarioSelecionado(int idUsuarioSelecionado) {
		this.idUsuarioSelecionado = idUsuarioSelecionado;
	}
	public String getNomeSelecionado() {
		return nomeSelecionado;
	}
	public void setNomeSelecionado(String nomeSelecionado) {
		this.nomeSelecionado = nomeSelecionado;
	}
	public String getEmailSelecionado() {
		return emailSelecionado;
	}
	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}
	public String getSenhaSelecionado() {
		return senhaSelecionado;
	}
	public void setSenhaSelecionado(String senhaSelecionado) {
		this.senhaSelecionado = senhaSelecionado;
	}
	public String getNumeroSelecionado() {
		return numeroSelecionado;
	}
	public void setNumeroSelecionado(String numeroSelecionado) {
		this.numeroSelecionado = numeroSelecionado;
	}
	public String getTipoSelecionado() {
		return tipoSelecionado;
	}
	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}
	public Date getDataSelecionado() {
		return dataSelecionado;
	}
	public void setDataSelecionado(Date dataSelecionado) {
		this.dataSelecionado = dataSelecionado;
	}
	public int getTelefoneSelecionado() {
		return telefoneSelecionado;
	}
	public void setTelefoneSelecionado(int telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	public List<Usuario> getUsuarios() {
		usuarios = FacadeControl.getInstanciaUsuario().listUsers();
		return usuarios;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String registerUserss() {
		Usuario emailExistente = FacadeControl.getInstanciaUsuario().checkExistenceEmail(usuario.getEmail());
		if (emailExistente == null) {
			Date dataAtual = new Date();
			usuario.setData(dataAtual);
			FacadeControl.getInstanciaUsuario().registerUsers(usuario);

			usuario = new Usuario();
		}else {
		FacesContext.getCurrentInstance().addMessage("msgValidador", 
				new FacesMessage("Já existe um cadastro com esse email."));
		return null ;
		}
		return "principal.xhtml?faces-redirect=true";
	}

	public String cadastrarInicial() {

		Date dataAtual = new Date();
		usuario.setData(dataAtual);
		FacadeControl.getInstanciaUsuario().registerUsers(usuario);

		usuario = new Usuario();
		return "login.xhtml?faces-redirect=true";
	}
	public String deleteUsers() {

		String id = SessionContext.getInstance().getParametroId("idUsuario");
		int idUsuario = Integer.parseInt(id);

		FacadeControl.getInstanciaUsuario().deleteUsers(idUsuario);

		return null;
	}
	
	public String redirecioneEditar() {

		String id = SessionContext.getInstance().getParametroId("idUsuario");
		 int idUsuario = Integer.parseInt(id);

		List<Usuario> usuarios = FacadeControl.getInstanciaUsuario().listUsers();
		for (Usuario usuario : usuarios) {

			if (usuario.getId() == idUsuario) {
				
				idUsuarioSelecionado =  usuario.getId() ;
				nomeSelecionado = usuario.getNome();
				emailSelecionado = usuario.getEmail();
				senhaSelecionado = usuario.getSenha();
				telefoneSelecionado = usuario.getTelefone();
				tipoSelecionado = usuario.getTipoTelefone();
				dataSelecionado = usuario.getData();
			}
		}

		PrimeFaces.current().executeScript("$('.editarUsuario').modal()");

		return null;
	}

	public void updateUsers() {
		
		Usuario usuario = new Usuario ();
		usuario.setData(dataSelecionado);
		usuario.setNome(nomeSelecionado);
		usuario.setEmail(emailSelecionado);
		usuario.setTelefone(telefoneSelecionado);
		usuario.setTipoTelefone(tipoSelecionado);
		usuario.setSenha(senhaSelecionado);
		usuario.setId(idUsuarioSelecionado);
		
		FacadeControl.getInstanciaUsuario().updateUsers(usuario);

	}
	
	public String redirecioneDetalhes() {
		String id = SessionContext.getInstance().getParametroId("idUsuario");
		int idUsuario = Integer.parseInt(id);

		List<Usuario> usuarios = FacadeControl.getInstanciaUsuario().listUsers();
		for (Usuario usuario: usuarios) {

			if (usuario.getId() == idUsuario) {

				idUsuarioSelecionado =  usuario.getId() ;
				nomeSelecionado = usuario.getNome();
				emailSelecionado = usuario.getEmail();
				senhaSelecionado = usuario.getSenha();
				telefoneSelecionado = usuario.getTelefone();
				tipoSelecionado = usuario.getTipoTelefone();
				dataSelecionado = usuario.getData();

			}
		}

		PrimeFaces.current().executeScript("$('.usuarioDetalhe').modal()");
		return null;

	}


}


//
