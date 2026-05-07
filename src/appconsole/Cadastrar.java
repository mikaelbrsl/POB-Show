/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package appconsole;

import jakarta.persistence.EntityManager;
import util.Util;

public class Cadastrar {
	private EntityManager manager;
	
	public Cadastrar() {
		try {
			Util.conectar();
			manager = Util.getManager();
			
			System.out.println("Cadastrando shows, artistas e cidades....");
			// Pessoa p;
			
			// // Cadastrando pessoas
			// manager.getTransaction().begin();
			// p = new Pessoa("joao");
			// p.setDtnascimento("01/01/1980");
			// p.adicionar(new Telefone("988881111"));
			// p.adicionar(new Telefone("988882222"));
			// p.setApelidos(List.of("jo", "joaozinho")  );
			// manager.persist(p);
			// manager.getTransaction().commit();
			
			// manager.getTransaction().begin();
			// p = new Pessoa("maria");
			// p.setDtnascimento("02/02/1980");
			// p.adicionar(new Telefone("988883333"));
			// p.adicionar(new Telefone("988884444"));
			// p.adicionar(new Telefone("32470000"));
			// p.setApelidos(List.of("mary", "mar")  );
			// manager.persist(p);
			// manager.getTransaction().commit();

			// manager.getTransaction().begin();
			// p = new Pessoa("jose");
			// p.setDtnascimento("01/01/1990");
			// p.adicionar(new Telefone("988885555"));
			// p.setApelidos(List.of("ze","jo")  );
			// manager.persist(p);
			// manager.getTransaction().commit();

			
			// // Cadastrando alunos
			// manager.getTransaction().begin();
			// p = new Aluno("paulo",9);
			// p.setDtnascimento("02/02/1990");
			// p.adicionar(new Telefone("988886666"));
			// p.setApelidos(List.of("paulao")  );
			// manager.persist(p);
			// manager.getTransaction().commit();
			
			// manager.getTransaction().begin();
			// p = new Aluno("ana",10);
			// p.setDtnascimento("02/02/1990");
			// p.adicionar(new Telefone("988887777"));
			// p.setApelidos(List.of("aninha")  );
			// manager.persist(p);
			// manager.getTransaction().commit();
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}


		Util.desconectar();
		System.out.println("fim do programa");
	}


	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}

}
