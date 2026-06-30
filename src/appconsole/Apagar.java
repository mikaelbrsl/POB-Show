/***********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ***********************************/
package appconsole;

import java.util.List;
import modelo.Show;
import requisito.FachadaShow;

public class Apagar {

    public Apagar() {
        try {
            System.out.println("Tarefa: Deletar shows agendados na cidade Natal");

            List<Show> todosShows = FachadaShow.listarShows();
            int quant = 0;

            for (Show s : todosShows) {
                if (s.getCidade() != null && s.getCidade().getNome().equalsIgnoreCase("Natal")) {
                    FachadaShow.apagarShow(s.getId());
                    quant++;
                }
            }

            System.out.println(quant + " Shows deletados.");

        } catch (Exception e) {
            System.out.println("Problema ao apagar: " + e.getMessage());
        }

        System.out.println("\nFim do programa.");
    }

    public static void main(String[] args) {
        new Apagar();
    }
}