/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import jakarta.persistence.EntityManager;
import util.Util;

public class Consultar {

    private EntityManager manager;

    public Consultar() {
        try {
            Util.conectar();
            manager = Util.getManager();

            

            


             // List<Pessoa> pessoas;
                // TypedQuery<Pessoa> q1;
                // TypedQuery<Long> q2;
                // System.out.println("\n--- pessoas em ordem alfabetica ");
                // q1 = manager.createQuery("""
                // 		select  p from Pessoa p order by p.nome""",
                // 		Pessoa.class);
                // pessoas = q1.getResultList();
                // for (Pessoa p : pessoas) System.out.println(p);

           
            //db4o(codigo antigo)

            // System.out.println("\n---quais os shows na data 05/07/2026");
            // Query q = manager.query();
            // q.constrain(Show.class);
            // q.descend("data").constrain("05/07/2026");
            // List<Show> resultados = q.execute();
            // for (Show show : resultados) {
            //     System.out.println(show);
            // }
            // System.out.println("\n---quais os artistas que vao se apresentar na cidade de nome 'João Pessoa'");
            // q = manager.query();
            // q.constrain(Show.class);
            // q.descend("cidade").descend("nome").constrain("João Pessoa");
            // resultados = q.execute();
            // for (Show show : resultados) {
            //     Artista artista = show.getArtista();
            //     System.out.println(artista);
            // }
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
            }catch (Exception e) {
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
