/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import util.Util;

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
            System.out.println(quant + "Shows deletados.");



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
