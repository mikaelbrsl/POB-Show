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
            select s from show20241370035 s 
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
            select a from artista20241370035 a
            join a.listaDeShow ls
            join ls.cidade c
            where c.nome like :nomeCidade""";

            q2 = manager.createQuery(selectQueryString, Artista.class);
            q2.setParameter("nomeCidade", "João Pessoa");
            artistas = q2.getResultList();
            for (Artista a : artistas) {
                System.out.println(a);
            }

            System.out.println("\n---quais os artistas que tem mais de 1 shows na cidade 'Natal'");

          
            //codigo db40
            
            // System.out.println("\n---quais os artistas que tem mais de 1 shows na cidade 'Natal'");
            // q = manager.query();
            // q.constrain(Artista.class);
            // q.constrain(new Filtro1(1));
            // List<Artista> artistas = q.execute();
            // for (Artista artista : artistas) {
            //     System.out.println(artista);
            // }
            // Util.desconectar();
            // System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
            // class Filtro1 implements Evaluation {
            //     private int n;
            //     public Filtro1(int n) {
            //         this.n = n;
            //     }
            //     public void evaluate(Candidate candidate) {
            //         Artista a = (Artista) candidate.getObject();
            //         List<Show> shows = a.getListaDeShow();
            //         int cont = 0;
            //         for (Show s : shows) {
            //             if (s.getCidade().getNome().equals("Natal")) {
            //                 cont++;
            //             }
            //         }
            //         if (cont > n) {
            //             candidate.include(true);
            //         } else {
            //             candidate.include(false);
            //         }
            //     }



            // CODIGO FAUSTO JPA
            // System.out.println("\n--- pessoas nascidas no mes 02");
            // q1 = manager.createQuery("""
            // 		select p from Pessoa p where extract(month from dtnascimento) = :mes""", 
            // 		Pessoa.class);
            // q1.setParameter("mes",2);
            // pessoas = q1.getResultList();
            // for (Pessoa p : pessoas) System.out.println(p);
            // System.out.println("\n--- pessoas com apelido contendo 'jo'");
            // q1 = manager.createQuery("""
            // 		select  p from Pessoa p join p.apelidos a where a like :x """,
            // 		Pessoa.class);
            // q1.setParameter("x", "%jo%");
            // pessoas = q1.getResultList();
            // for (Pessoa p : pessoas) System.out.println(p);
            // System.out.println("\n--- pesoas com telefone fixo");
            // q1 = manager.createQuery("""
            // 		select p from Pessoa p join p.telefones t
            // 		where t.numero like :prefixo""", 
            // 		Pessoa.class);
            // q1.setParameter("prefixo", "3%"); 
            // pessoas = q1.getResultList();
            // for (Pessoa p : pessoas) System.out.println(p);
            // System.out.println("\n--- pessoas com 2 telefones");
            // q1 = manager.createQuery("""
            // 		select p from Pessoa p	where SIZE(p.telefones) = :x""", 
            // 		Pessoa.class);
            // q1.setParameter("x", 2);
            // pessoas = q1.getResultList();
            // for (Pessoa p : pessoas) System.out.println(p); 
            // System.out.println("\n--- total de pessoas");
            // q2 = manager.createQuery("""
            // 		select count(p) from Pessoa p""", 
            // 		Long.class);
            // Long cont = q2.getSingleResult();
            // System.out.println(cont);
            // System.out.println("\n--- total de alunos");
            // q2 = manager.createQuery("""
            // 		select count(a) from Aluno a""", 
            // 		Long.class);
            // cont = q2.getSingleResult();
            // System.out.println(cont);
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
