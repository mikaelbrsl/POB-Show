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

import modelo.Aluno;
import modelo.Pessoa;
import repositorio.Repositorio;
import repositorio.RepositorioAluno;
import repositorio.RepositorioPessoa;

public class FachadaAluno {
	private FachadaAluno() {}

	private static RepositorioAluno repAluno = new RepositorioAluno();
	private static RepositorioPessoa repPessoa = new RepositorioPessoa();

	public static Aluno localizarAluno(String nome) throws Exception {
		try {
			Repositorio.conectar();
			Aluno a = repAluno.localizar(nome);
			return a;
		} catch (Exception e) {
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}



	public static void criarAluno(String nome, String data, List<String> apelidos, double nota) throws Exception {
		try {
			try {
				LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException e) {
				throw new Exception("formato data invalido:" + data);
			}

			Repositorio.conectar();
			Repositorio.begin();

			Pessoa p = repPessoa.localizar(nome); 	// qualquer pessoa
			if (p != null)
				throw new Exception("criar aluno - nome ja existe:" + nome);

			if (nota<0 && nota>10.0)
				throw new Exception("criar aluno - nota invalida:" + nota);
			
			
			Aluno a = new Aluno(nome, nota);
			a.setDtNascimento(data);
			a.setApelidos(apelidos);
			
			repAluno.criar(a);
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static void alterarAluno(String nome, String data, List<String> apelidos, double nota) throws Exception {
		try {
			Repositorio.conectar();
			Repositorio.begin();
			Aluno alu = repAluno.localizar(nome);
			if (alu == null)
				throw new Exception("alterar aluno - nome inexistente:" + nome);

			alu.setApelidos(apelidos); 

			if (data != null)
				try {
					LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					alu.setDtNascimento(data);
				} catch (DateTimeParseException e) {
					throw new Exception("alterar aluno - formato data invalido:" + data);
				}
			alu.setNota(nota);
			
			repAluno.atualizar(alu); 		
			Repositorio.commit();

		} catch (Exception e) {
			Repositorio.rollback();
			throw e;
		} finally {
			Repositorio.desconectar();
		}
	}

	public static List<Aluno> listarAlunos() {
		Repositorio.conectar();
		List<Aluno> result = repAluno.listar();
		Repositorio.desconectar();
		return result;
	}


	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS REPOSITORIOS
	 * 
	 **********************************************************/

	
}
