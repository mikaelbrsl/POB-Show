package requisito;

import java.util.List;

import modelo.Pessoa;
import modelo.Telefone;
import repositorio.Repositorio;
import repositorio.RepositorioPessoa;
import repositorio.RepositorioTelefone;

public class FachadaTelefone {
	private FachadaTelefone() {}

	private static RepositorioTelefone repTelefone = new RepositorioTelefone();
	private static RepositorioPessoa repPessoa = new RepositorioPessoa();


	public static Telefone localizarTelefone(String num) throws Exception {
		try {
			Repositorio.conectar();
			Telefone t = repTelefone.localizar(num);
			return t;
			
		} catch (Exception e) {
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void criarTelefone( String numero, String nome) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome);
			if (p == null)
				throw new Exception("criar telefone - nome inexistente" + nome);

			Telefone t = repTelefone.localizar(numero);
			if (t != null)
				throw new Exception("criar telefone - numero ja cadastrado:" + numero);

			if (numero.isEmpty())
				throw new Exception("criar telefone - numero vazio:" + numero);

			t = new Telefone(numero);
			p.adicionar(t);
			
			repTelefone.criar(t);
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void apagarTelefone(String numero) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Telefone t = repTelefone.localizar(numero);
			if (t == null)
				throw new Exception("excluir telefone - numero inexistente:" + numero);

			Pessoa p = t.getPessoa();
			p.remover(t);
			
			repTelefone.deletar(t); 	
			repPessoa.atualizar(p); 	
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void alterarNumero(String numero, String novonumero) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Telefone t1 = repTelefone.localizar(numero);
			if (t1 == null) 
				throw new Exception("alterar numero - numero inexistente:" + numero);
			
			Telefone t2 = repTelefone.localizar(novonumero);
			if (t2 != null) 
				throw new Exception("alterar numero - novo numero ja existe:" + novonumero);
			
			if (novonumero.isEmpty()) 
				throw new Exception("alterar numero - novo numero vazio:");
			
			t1.setNumero(novonumero); 		// substituir
			
			repTelefone.atualizar(t1);	// automatico
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	
	public static List<Telefone> listarTelefones() {
		Repositorio.conectar();
		List<Telefone> result = repTelefone.listar();
		Repositorio.desconectar();
		return result;
	}

	public static List<Telefone> listarTelefones(String texto) {
		Repositorio.conectar();
		List<Telefone> result = repTelefone.listarPorNumero(texto);
		Repositorio.desconectar();
		return result;
	}

	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS REPOSITORIOS
	 * 
	 **********************************************************/

	

}
