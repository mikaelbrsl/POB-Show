/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import util.Util;

public class Alterar {

    private EntityManager manager;

    public Alterar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            //db4o(CODIGO ANTIGO)

            // alterando cidade do show 1 para Recife

            // Query q = manager.query();
            // q.constrain(Show.class);
            // q.descend("id").constrain(1);
            // List<Show> resultados = q.execute();
            // if (resultados.size() > 0) {
            // 	Show show = resultados.getFirst();
            // 	System.out.println("Show " + show.getId() + " encontrado." );
            // 	Cidade cidade = show.getCidade();
            // 	q = manager.query();
            // 	q.constrain(Cidade.class);
            // 	q.descend("nome").constrain("Recife");
            // 	List<Cidade> resultados2 = q.execute();
            // 	if (resultados2.size() > 0) {
            // 		Cidade novaCidade = resultados2.getFirst();
            // 		show.remover(cidade);
            // 		show.adicionar(novaCidade);
            // 	manager.store(show);
            // 		manager.store(show);
            // 		System.out.println("Show " + show.getId() + " alterado para cidade " + novaCidade.getNome());
            // 	} else
            // 		System.out.println("Cidade 'Recife' não encontrada.");
            // Util.desconectar();
            // System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");




            //CODIGO FAUSTO JPA

            // manager.getTransaction().begin();
            // TypedQuery<Pessoa> q = manager.createQuery(
            // 		"select p from Pessoa p where p.nome = 'joao' ", Pessoa.class);
            // Pessoa p = q.getSingleResult();
            // p.setNome("joana");
            // Telefone t = p.getTelefones().getLast(); 
            // p.remover(t);  			
            //manager.merge(p); //nao necessita de atualiza��o (� automatico)
            //manager.merge(t); //nao necessita de atualiza��o (� automatico)
            // manager.getTransaction().commit();
            // System.out.println("alterou nome para joana");
            // System.out.println("removeu ultimo telefone ");

			
        } catch (NonUniqueResultException e) {
            manager.getTransaction().rollback();
            System.out.println("encontrou nome duplicado no banco ");
        } catch (NoResultException e) {
            manager.getTransaction().rollback();
            System.out.println("nao encontrou nome no banco ");
        } catch (Exception e) {
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
