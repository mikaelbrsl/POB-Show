/***********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ***********************************/
package appconsole;

import java.time.LocalDate;
import java.util.List;
import modelo.Artista;
import modelo.Show;
import requisito.FachadaShow;

public class Consultar {

    public Consultar() {
        try {
            // --- Consulta 1 ---
            System.out.println("\n--- Quais os shows na data 05/07/2026 ---");
            LocalDate dataBusca = LocalDate.of(2026, 7, 5);
            List<Show> shows = FachadaShow.consultarShowsNaData(dataBusca);
            for (Show s : shows) {
                System.out.println(s);
            }

            // --- Consulta 2 ---
            System.out.println("\n--- Quais os artistas que têm shows na cidade 'João Pessoa' ---");
            List<Artista> artistasJP = FachadaShow.consultarArtistasNaCidade("João Pessoa");
            for (Artista a : artistasJP) {
                System.out.println(a.getNomeArtistico());
            }

            // --- Consulta 3 ---
            System.out.println("\n--- Quais os artistas que têm mais de 1 show na cidade 'Campina Grande' ---");
            List<Artista> artistasCG = FachadaShow.consultarArtistasComMaisDeNShowsNaCidade("Campina Grande", 1);
            for (Artista a : artistasCG) {
                System.out.println(a.getNomeArtistico());
            }

        } catch (Exception e) {
            System.out.println("Erro na consulta: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nFim do programa.");
    }

    public static void main(String[] args) {
        new Consultar();
    }
}