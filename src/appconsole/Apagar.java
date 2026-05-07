/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import util.Util;

public class Apagar {

    private EntityManager manager;

    public Apagar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            //db40(CODIGO ANTIGO)

            // OBS: Deletando todos os objetos do banco para retestes

			//Query q = manager.query();
			//q.constrain(Artista.class);  				
			//List<Artista> resultados = q.execute();
			//for (Artista art : resultados ) {
			//		manager.delete(art);
			//}
			//		
			//q = manager.query();
			//q.constrain(Cidade.class);  				
			//List<Cidade> resultados2 = q.execute();
			//for (Cidade cid : resultados2 ) {
			//		manager.delete(cid);
			//}
			//		
			//q = manager.query();
			//q.constrain(Show.class);  				
			//List<Show> resultados3 = q.execute();
			//	for (Show show : resultados3 ) {
			//		manager.delete(show);
			//	}
			// finaliza aqui
			// ...


            // Query q = manager.query();
            // q.constrain(Cidade.class);
            // q.descend("nome").constrain("Natal");
            // List<Cidade> cidade = q.execute();
            // Cidade c = cidade.getFirst();
            // List<Show> show = c.getListaDeShow();
            // if (!show.isEmpty()) {
            // 	for(Show s : show) {
            // 		manager.delete(s);
            // 	}
            // }
            // manager.delete(c);
            // manager.commit();



            //CODIGO DE FAUSTO JPA

            // System.out.println("tarefa: deletar joana e seus telefones - orfaos");
            // manager.getTransaction().begin();
            // TypedQuery<Pessoa> q = manager.createQuery(
            // 		"select p from Pessoa p where p.nome = 'joana' ", Pessoa.class);
            // Pessoa joana = q.getSingleResultOrNull();
            // if(joana == null) {
            // 	System.out.println("nome inexistente no banco");
            // 	return;
            // }
            // joana.getApelidos().clear(); //remover apelidos
            // for(Telefone t : joana.getTelefones()) {
            // 	t.setPessoa(null); 	
            // }
            // manager.remove(joana);	//deletar joana - telefones orfaos serao deletados
            // manager.getTransaction().commit();
            // System.out.println("deletou com sucesso");


        } catch (NonUniqueResultException e) {
            manager.getTransaction().rollback();
            System.out.println("nome duplicado ");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("problema:" + e.getMessage());
        }

        Util.desconectar();
        System.out.println("fim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new Apagar();
    }

}
