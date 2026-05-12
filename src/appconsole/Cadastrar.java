/** ********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 ********************************* */
package appconsole;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import modelo.Artista;
import modelo.Cidade;
import modelo.Show;
import util.Util;

public class Cadastrar {

    private EntityManager manager;

    public Cadastrar() {
        try {
            Util.conectar();
            manager = Util.getManager();
            manager.getTransaction().begin();

            System.out.println("Cadastrando cidades, artistas e shows...");

            Cidade c1 = new Cidade("João Pessoa", new ArrayList<>());
            Cidade c2 = new Cidade("Campina Grande", new ArrayList<>());
            Cidade c3 = new Cidade("Recife", new ArrayList<>());
            Cidade c4 = new Cidade("Natal", new ArrayList<>());
            Cidade c5 = new Cidade("Fortaleza", new ArrayList<>());

            Artista a1 = new Artista("Alok", new ArrayList<>());
            Artista a2 = new Artista("Anitta", new ArrayList<>());
            Artista a3 = new Artista("Anderson Neiff", new ArrayList<>());
            Artista a4 = new Artista("Ivete Sangalo", new ArrayList<>());
            Artista a5 = new Artista("Luan Santana", new ArrayList<>());

            Show s1 = new Show(LocalDate.of(2026, 5, 20), c1, a1);
            a1.adicionar(s1);
            c1.adicionar(s1);
            manager.persist(s1);

            Show s2 = new Show(LocalDate.of(2026, 5, 21), c1, a2);
            a2.adicionar(s2);
            c1.adicionar(s2);
            manager.persist(s2);

            Show s3 = new Show(LocalDate.of(2026, 6, 10), c2, a1);
            a1.adicionar(s3);
            c2.adicionar(s3);
            manager.persist(s3);

            Show s4 = new Show(LocalDate.of(2026, 6, 12), c2, a4);
            a4.adicionar(s4);
            c2.adicionar(s4);
            manager.persist(s4);

            Show s5 = new Show(LocalDate.of(2026, 7, 5), c3, a3);
            a3.adicionar(s5);
            c3.adicionar(s5);
            manager.persist(s5);

            Show s6 = new Show(LocalDate.of(2026, 7, 5), c1, a2);
            a2.adicionar(s6);
            c1.adicionar(s6);
            manager.persist(s6);

            Show s7 = new Show(LocalDate.of(2026, 8, 15), c4, a4);
            a4.adicionar(s7);
            c4.adicionar(s7);
            manager.persist(s7);

            Show s8 = new Show(LocalDate.of(2026, 8, 16), c4, a3);
            a3.adicionar(s8);
            c4.adicionar(s8);
            manager.persist(s8);

            Show s9 = new Show(LocalDate.of(2026, 8, 17), c2, a5);
            a5.adicionar(s9);
            c2.adicionar(s9);
            manager.persist(s9);

            Show s10 = new Show(LocalDate.of(2026, 8, 18), c2, a5);
            a5.adicionar(s10);
            c2.adicionar(s10);
            manager.persist(s10);

            Show s11 = new Show(LocalDate.of(2026, 8, 19), c4, a3);
            a3.adicionar(s11);
            manager.persist(s11);

            manager.persist(c5);

            manager.getTransaction().commit();

        } catch (Exception e) {
            // Caso algo dê errado em qualquer um, desfaz tudo (Atomicidade)
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        Util.desconectar();
        System.out.println("fim do programa3");
    }

    // =================================================
    public static void main(String[] args) {
        new Cadastrar();
    }

}
