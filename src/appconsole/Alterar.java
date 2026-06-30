/***********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ***********************************/
package appconsole;

import modelo.Show;
import requisito.FachadaShow;

public class Alterar {

    public Alterar() {
        try {
            System.out.println("Tarefa: alterar cidade de um show.");

            Show s = FachadaShow.localizarShow(1);
            System.out.println("Show " + s.getId() + " encontrado.");

            
            FachadaShow.alterarShow(s.getId(), s.getRawData(), "Recife", s.getArtista().getNomeArtistico());
            
            System.out.println("Cidade do show " + s.getId() + " alterada para Recife.");

        } catch (Exception e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
        }

        System.out.println("\nFim do programa.");
    }

    public static void main(String[] args) {
        new Alterar();
    }
}