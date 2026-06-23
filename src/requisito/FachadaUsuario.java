package requisito;

import java.util.List;

import modelo.Usuario;
import repositorio.Repositorio;
import repositorio.RepositorioUsuario;

public class FachadaUsuario {
	private FachadaUsuario() {}

	private static Usuario logado = null;
	private static RepositorioUsuario repUsuario = new RepositorioUsuario();

	public static Usuario localizarUsuario(String nome, String senha) throws Exception {
		try {
			Repositorio.conectar();
			Usuario u = repUsuario.localizarComSenha(nome,senha);
			return u;
		} catch (Exception e) {
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void criarUsuario(String nome, String senha) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();

			Usuario u = repUsuario.localizar(nome);
			if (u != null)
				throw new Exception("usuario ja existe:" + nome);

			u = new Usuario(nome, senha);
			
			repUsuario.criar(u);
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}


	public static List<Usuario> listarUsuarios() {
		Repositorio.conectar();
		List<Usuario> result = repUsuario.listar();
		Repositorio.desconectar();
		return result;
	}

	
	public static Usuario getLogado() {
		return logado;
	}

	public static void setLogado(Usuario logado) {
		FachadaUsuario.logado = logado;
	}
	
}
