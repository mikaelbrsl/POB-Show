/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import java.util.List;


import modelo.Artista;
import modelo.Cidade;
import modelo.Show;
import requisito.FachadaArtista;
import requisito.FachadaCidade;
import requisito.FachadaShow;


public class Listar {



    public Listar() {
        try {
            System.out.println("\n----------------------------------------");
            System.out.println("LISTAGEM DE ARTISTAS");
            System.out.println("----------------------------------------");
            List<Artista> resultados1 = FachadaArtista.listarArtistas();
            for (Artista a : resultados1) {
                System.out.println(a);
                System.out.println();
            }

            System.out.println("\n----------------------------------------");
            System.out.println("LISTAGEM DE CIDADES");
            System.out.println("----------------------------------------");
            List<Cidade> resultados2 = FachadaCidade.listarCidades();
            for (Cidade c : resultados2) {
                System.out.println(c);
                System.out.println();
            }

            System.out.println("\n----------------------------------------");
            System.out.println("LISTAGEM DE SHOWS");
            System.out.println("----------------------------------------");
            List<Show> resultados3 = FachadaShow.listarShows();
            for (Show s : resultados3) {
                System.out.println(s);
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Erro durante a listagem dos dados: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("fim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new Listar();
    }

}
