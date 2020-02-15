package model.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import control.facade.FacadeControl;
import control.users.SessionContext;
import model.users.Usuario;


@ManagedBean
@RequestScoped
public class ControlAcessBeans {

	private Usuario usuario;

	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logar() {  

		Usuario logar = FacadeControl.getInstanciaUsuario().loginAcessUsers(usuario.getEmail(), usuario.getSenha());
		if (logar != null) {

			SessionContext.getInstance().setAttribute("usuariologado", usuario);
			System.out.println("Logado com sucesso");
			return "/principal/principal.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage("msgValidador", 
				new FacesMessage("Email ou senha incorretos"));
    
		return null;
	}

	public String logout() {

		SessionContext.getInstance().encerrarSessao();
		return "/login.xhtml?faces-redirect=true";
	}

	public String cadastroInicial() {
		if (usuario != null) {
			Date dataAtual = new Date();
			usuario.setData(dataAtual);
			FacadeControl.getInstanciaUsuario().registerUsers(usuario);
			return "/login.xhtml?faces-redirect=true";
		}

		return "/cadastroInicial.xhtml?faces-redirect=true";
	}

}
