/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pessoa;
import modelo.Telefone;
import util.Util;

public class Apagar {
	private EntityManager manager;

	public Apagar() {
		try {
			Util.conectar();
			manager = Util.getManager();
			
			System.out.println("tarefa: deletar joana e seus telefones - orfaos");
			
			manager.getTransaction().begin();
			TypedQuery<Pessoa> q = manager.createQuery(
					"select p from Pessoa p where p.nome = 'joana' ", Pessoa.class);
			Pessoa joana = q.getSingleResultOrNull();
			if(joana == null) {
				System.out.println("nome inexistente no banco");
				return;
			}
			
			joana.getApelidos().clear(); //remover apelidos
			for(Telefone t : joana.getTelefones()) {
				t.setPessoa(null); 	
			}
			manager.remove(joana);	//deletar joana - telefones orfaos serao deletados
			manager.getTransaction().commit();
			
			System.out.println("deletou com sucesso");
		
		} catch (NonUniqueResultException e) {
			manager.getTransaction().rollback();
			System.out.println("nome duplicado ");
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println("problema:"+e.getMessage());
		}


		Util.desconectar();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Apagar();
	}

}
