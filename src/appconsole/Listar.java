/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package appconsole;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Aluno;
import modelo.Pessoa;
import modelo.Telefone;
import util.Util;

public class Listar {
	private  EntityManager manager;

	public Listar() {
		try {
			Util.conectar();
			manager = Util.getManager();
			
			System.out.println("\nListagem de pessoas");
			TypedQuery<Pessoa> query1 = manager.createQuery("select p from Pessoa p", Pessoa.class); // order by p.nome
			List<Pessoa> resultados1 = query1.getResultList();
			for (Pessoa p : resultados1)
				System.out.println(p);

			System.out.println("\nListagem de alunos");
			TypedQuery<Aluno> query2 = manager.createQuery("select a from Aluno a", Aluno.class); // order by p.nome
			List<Aluno> resultados2 = query2.getResultList();
			for (Aluno p : resultados2)
				System.out.println(p);

			System.out.println("\nListagem de telefones");
			TypedQuery<Telefone> query3 = manager.createQuery("select t from Telefone t order by t.id", Telefone.class); // order by p.nome
			List<Telefone> resultados3 = query3.getResultList();
			for (Telefone t : resultados3)
				System.out.println(t);

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		Util.desconectar();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Listar();
	}

}
