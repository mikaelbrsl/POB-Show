/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pessoa;
import modelo.Telefone;
import util.Util;

public class Alterar {
	private EntityManager manager;

	public Alterar() {
		try {
			Util.conectar();
			manager = Util.getManager();

			manager.getTransaction().begin();
			TypedQuery<Pessoa> q = manager.createQuery(
					"select p from Pessoa p where p.nome = 'joao' ", Pessoa.class);
			Pessoa p = q.getSingleResult();

			p.setNome("joana");
			Telefone t = p.getTelefones().getLast(); 
			p.remover(t);  	//bidirecional		
			
			//manager.merge(p); //nao necessita de atualização (é automatico)
			//manager.merge(t); //nao necessita de atualização (é automatico)
			manager.getTransaction().commit();

			System.out.println("alterou nome para joana");
			System.out.println("removeu ultimo telefone ");
			
		} 
		catch (NonUniqueResultException e) {
			manager.getTransaction().rollback();
			System.out.println("encontrou nome duplicado no banco ");
		}
		catch (NoResultException e) {
			manager.getTransaction().rollback();
			System.out.println("nao encontrou nome no banco ");
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}


		Util.desconectar();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Alterar();
	}

}
