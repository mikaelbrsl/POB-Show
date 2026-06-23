/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranhão Ayres
 **********************************/
package repositorio;

import java.util.List;

import jakarta.persistence.TypedQuery;
import modelo.Aluno;
import util.Util;

public class RepositorioAluno extends Repositorio<Aluno> {

	public Aluno localizar(Object chave) {
		String nome = (String) chave;
		TypedQuery<Aluno> q = Util.getManager().createQuery("""
				select a from Aluno a
				LEFT JOIN FETCH a.telefones
				LEFT JOIN FETCH a.apelidos
				where a.nome=:n""", Aluno.class);
		q.setParameter("n", nome);

		return q.getSingleResultOrNull();
	}
	
	public List<Aluno> listar() {
		TypedQuery<Aluno> q = Util.getManager().createQuery("""
				select p from Aluno p
				order by p.id
				""", Aluno.class);
		return q.getResultList();
	}

}
