/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Artista;
import modelo.Cidade;
import modelo.Show;
import util.Util;

import java.util.List;

public class Listar {

    private EntityManager manager;

    public Listar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            System.out.println("\nListagem de pessoas");
             TypedQuery<Artista> query1 = manager.createQuery("select a from artista20241370035 a", Artista.class); // order by p.nome
             List<Artista> resultados1 = query1.getResultList();
             for (Artista a : resultados1)
             	System.out.println(a);

            System.out.println("\nListagem de Cidades");
            TypedQuery<Cidade> query2 = manager.createQuery("select c from cidade20241370035 c", Cidade.class); // order by p.nome
            List<Cidade> resultados2 = query2.getResultList();
            for (Cidade c : resultados2)
                System.out.println(c);

            System.out.println("\nListagem de Shows");
            TypedQuery<Show> query3 = manager.createQuery("select s from show20241370035 s", Show.class); // order by p.nome
            List<Show> resultados3 = query3.getResultList();
            for (Show s : resultados3)
                System.out.println(s);

        } catch (Exception e) {
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
