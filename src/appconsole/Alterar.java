/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Cidade;
import modelo.Show;
import util.Util;

public class Alterar {

    private EntityManager manager;

    public Alterar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            System.out.println("tarefa: alterar cidade de um show.");
            manager.getTransaction().begin();
            TypedQuery<Show> q = manager.createQuery(
                    "select s from show20241370035 s where s.id = 1", Show.class);
            Show s = q.getSingleResult();

            System.out.println("Show " + s.getId() + " encontrado.");

            Cidade oldCidade = s.getCidade();

            TypedQuery<Cidade> q2 = manager.createQuery(
                    "select c from cidade20241370035 c where c.nome = 'Recife' ", Cidade.class
            );
            Cidade newCidade = q2.getSingleResult();

            s.setCidade(newCidade);
            oldCidade.remover(s);
            manager.getTransaction().commit();
            System.out.println("Cidade do show " + s.getId() + " alterado para " + newCidade.getNome());

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

        System.out.println("fim do programa2");
    }

    // =================================================
    public static void main(String[] args) {
        new Alterar();
    }

}
