package requisito;

/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import modelo.Pessoa;
import modelo.Telefone;
import repositorio.Repositorio;
import repositorio.RepositorioPessoa;

public class FachadaPessoa {
	private FachadaPessoa() {}

	private static RepositorioPessoa repPessoa = new RepositorioPessoa();


	public static Pessoa localizarPessoa(String nome) throws Exception {
		try {
			Repositorio.conectar();
			Pessoa p = repPessoa.localizar(nome);
			return p;

		} catch (Exception e) {
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}


	public static void criarPessoa(String nome, String data, List<String> apelidos) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			try {
				LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException e) {
				throw new Exception("formato data invalido:" + data);
			}
			
			Pessoa p = repPessoa.localizar(nome);
			if (p != null) 
				throw new Exception("criar pessoa - nome ja existe:" + nome);
			
			p = new Pessoa(nome);
			p.setDtNascimento(data);
			p.setApelidos(apelidos);
			
			repPessoa.criar(p);
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}


	public static void alterarPessoa(String nome, String data, List<String> apelidos) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome);
			if (p == null)
				throw new Exception("alterar pessoa - pessoa inexistente:" + nome);

			p.setApelidos(apelidos); // limpar apelidos atuais
			if (data != null)
				try {
					LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					p.setDtNascimento(data);
				} catch (DateTimeParseException e) {
					throw new Exception("alterar pessoa - formato data invalido:" + data);
				}
			
			repPessoa.atualizar(p); 
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}


	public static void alterarData(String nome, String data) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome);
			if (p == null)
				throw new Exception("alterar pessoa - pessoa inexistente:" + nome);

			if (data != null)
				try {
					LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					p.setDtNascimento(data);
				} catch (DateTimeParseException e) {
					throw new Exception("alterar data - formato data invalido:" + data);
				}

			repPessoa.atualizar(p); 		
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void alterarNome(String nome, String novonome) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome); // usando chave primaria
			if (p == null)
				throw new Exception("alterar nome - nome inexistente:" + nome);

			p.setNome(novonome);
			
			repPessoa.atualizar(p); 		
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void alterarFoto(String nome, byte[] foto) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome); // usando chave primaria
			if (p == null)
				throw new Exception("alterar foto - nome inexistente:" + nome);

			p.setFoto(foto);
			
			repPessoa.atualizar(p); 		
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void apagarPessoa(String nome) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Pessoa p = repPessoa.localizar(nome);
			if (p == null)
				throw new Exception("excluir pessoa - nome inexistente:" + nome);

			for (Telefone t : p.getTelefones())
				t.setPessoa(null); 	  // remover pessoa do telefone (orfão)

			p.getTelefones().clear(); 	// remover telefones
			p.getApelidos().clear(); 	// remover apelidos

			repPessoa.deletar(p); 	
			Repositorio.commit();
			
		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}


	public static List<Pessoa> listarPessoas() {
		Repositorio.conectar();
		List<Pessoa> lista = repPessoa.listar();
		Repositorio.desconectar();
		return lista;
	}

	public static List<Pessoa> listarPessoas(String texto) {
		Repositorio.conectar();
		List<Pessoa> lista = repPessoa.listar(texto);
		Repositorio.desconectar();
		return lista;
	}


	

	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS REPOSITORIOS
	 * 
	 **********************************************************/

	public static List<Pessoa> consultarMesNascimento(String mes) {
		Repositorio.conectar();
		List<Pessoa> result = repPessoa.listarPorMes(mes);
		Repositorio.desconectar();
		return result;
	}

	public static List<Pessoa> consultarPessoasNTelefones(int n) {
		Repositorio.conectar();
		List<Pessoa> result = repPessoa.listarPorNTelefones(n);
		Repositorio.desconectar();
		return result;
	}

	public static boolean temTelefoneFixo(String nome) {
		Repositorio.conectar();
		boolean resp = repPessoa.temTelefoneFixo(nome);
		Repositorio.desconectar();
		return resp;
	}

	public static List<Pessoa> consultarApelido(String ap) {
		Repositorio.conectar();
		List<Pessoa> result = repPessoa.listarPorApelido(ap);
		Repositorio.desconectar();
		return result;
	}

}
