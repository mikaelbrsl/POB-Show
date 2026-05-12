/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Cidade;
import modelo.Show;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Apagar {

    private EntityManager manager;

    public Apagar() {
        try {
            Util.conectar();
            manager = Util.getManager();
            manager.getTransaction().begin();
            System.out.println("tarefa: Deletar shows agendados na cidade Natal");

            Query q = manager.createQuery("DELETE FROM show20241370035 s WHERE s.cidade.nome = 'Natal'");
            int quant = q.executeUpdate();

            manager.getTransaction().commit();
            System.out.println("Shows deletados.");



        } catch (NonUniqueResultException e) {
            manager.getTransaction().rollback();
            System.out.println("nome duplicado ");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("problema:" + e.getMessage());
        }

        Util.desconectar();
        System.out.println("fim do programa4");
    }

    // =================================================
    public static void main(String[] args) {
        new Apagar();
    }

}
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