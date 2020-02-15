package control.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.Usuario;


@WebFilter("/principal/*")
public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse respo = (HttpServletResponse) response;
		 Usuario logado = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuariologado");

		if (logado == null) {
			respo.sendRedirect("/projetosefaz/login.xhtml?faces-redirect=true");
			System.out.println("Você não está logado");

		}else {

		chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
