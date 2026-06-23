/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranhão Ayres
 **********************************/
package repositorio;

import java.util.List;

import jakarta.persistence.TypedQuery;
import modelo.Telefone;
import util.Util;

public class RepositorioTelefone extends Repositorio<Telefone> {

	public Telefone localizar(Object chave) {
		String numero = (String) chave;
		TypedQuery<Telefone> q = Util.getManager().createQuery("""
				select t from Telefone t 
				LEFT JOIN FETCH t.pessoa p
				where t.numero = :n
				""", Telefone.class);
		q.setParameter("n", numero);

		return q.getSingleResultOrNull();
	}

	public List<Telefone> listar() {
		TypedQuery<Telefone> q = Util.getManager().createQuery("""
				select t from Telefone t order by t.id
				""", Telefone.class);

		return q.getResultList();
	}

	public List<Telefone> listarPorNumero(String digitos) {
		// carregar um telefone com seus relacionamentos
		TypedQuery<Telefone> q = Util.getManager().createQuery("""
				select t from Telefone t 
				where t.numero like :x
				""", Telefone.class);
		q.setParameter("x", "%" + digitos + "%");

		return q.getResultList();
	}
}
