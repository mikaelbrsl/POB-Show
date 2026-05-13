/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Artista;
import modelo.Show;
import util.Util;

public class Consultar {

    private EntityManager manager;

    public Consultar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            System.out.println("\n---quais os shows na data 05/07/2026");

            List<Show> shows;
            TypedQuery<Show> q1;

            LocalDate dataBusca = LocalDate.of(2026, 7, 5);
            String selectQueryString = """
            select s from Show s 
            where s.data = :dataShow""";

            q1 = manager.createQuery(selectQueryString, Show.class);
            q1.setParameter("dataShow", dataBusca);
            shows = q1.getResultList();

            for (Show s : shows) {
                System.out.println(s);
            }

            System.out.println("\n---quais os artistas que vao se apresentar na cidade de nome 'João Pessoa'");

            List<Artista> artistas;
            TypedQuery<Artista> q2;

            selectQueryString = """
            select a from Artista a
            join a.listaDeShow ls
            join ls.cidade c
            where c.nome like :nomeCidade""";

            q2 = manager.createQuery(selectQueryString, Artista.class);
            q2.setParameter("nomeCidade", "João Pessoa");
            artistas = q2.getResultList();
            for (Artista a : artistas) {
                System.out.println(a);
            }

            System.out.println("\n---quais os artistas que tem mais de 1 shows na cidade 'Campina Grande'");


            //implementação pronta - adiantado
            TypedQuery<Artista> q3 = manager.createQuery(
                                """
                            select a from Artista a 
                            join a.listaDeShow s 
                            where s.cidade.nome = :cidade
                            """, Artista.class);
            q3.setParameter("cidade", "Campina Grande" );
            q3.setParameter("quantidade", 1);



            List<Artista> lista = q3.getResultList();

            for (Artista a : lista) {
                System.out.println(a);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Util.desconectar();
        System.out.println("\nfim do programa");
    }
    // =================================================

    public static void main(String[] args) {
        new Consultar();
    }
}
