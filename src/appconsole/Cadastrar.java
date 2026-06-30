package appconsole;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import requisito.FachadaArtista;
import requisito.FachadaCidade;
import requisito.FachadaShow;

public class Cadastrar {

    public static void main(String[] args) {
        try {
            System.out.println("Cadastrando cidades..");
            FachadaCidade.criarCidade("João Pessoa");
            FachadaCidade.criarCidade("Campina Grande");
            FachadaCidade.criarCidade("Pilar");
            FachadaCidade.criarCidade("Guarabira");
            FachadaCidade.criarCidade("Patos");
            FachadaCidade.criarCidade("Sousa");
            FachadaCidade.criarCidade("Cajazeiras");
            FachadaCidade.criarCidade("Cabedelo");

            System.out.println("Cadastrando artistas...");
            FachadaArtista.criarArtista("Alok");
            FachadaArtista.criarArtista("Anitta");
            FachadaArtista.criarArtista("Anderson Neiff");
            FachadaArtista.criarArtista("Ivete Sangalo");
            FachadaArtista.criarArtista("Eminem");
            FachadaArtista.criarArtista("Sabrina Carpenter");
            FachadaArtista.criarArtista("Olivia Rodrigo");

            System.out.println("Vinculando fotos aos artistas...");
            atualizarFotoArtista("Alok", "/fotos/alok.jpeg");
            atualizarFotoArtista("Anitta", "/fotos/anitta.jpg");
            atualizarFotoArtista("Anderson Neiff", "/fotos/neiff.jpg");
            atualizarFotoArtista("Ivete Sangalo", "/fotos/ivete.jpg");
            atualizarFotoArtista("Eminem", "/fotos/eminem.jpg");
            atualizarFotoArtista("Sabrina Carpenter", "/fotos/sabrina.jpg");
            atualizarFotoArtista("Olivia Rodrigo", "/fotos/olivia.jpg");

            System.out.println("Cadastrando shows...");
            
            FachadaShow.criarShow(LocalDate.of(2026, 5, 20), "João Pessoa", "Alok");
            FachadaShow.criarShow(LocalDate.of(2026, 5, 26), "Pilar", "Alok");
            FachadaShow.criarShow(LocalDate.of(2026, 10, 26), "Pilar", "Alok");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 24), "Cabedelo", "Alok");

            FachadaShow.criarShow(LocalDate.of(2026, 7, 6), "Sousa", "Anitta");
            FachadaShow.criarShow(LocalDate.of(2026, 7, 5), "João Pessoa", "Anitta");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 15), "Guarabira", "Anitta");

            FachadaShow.criarShow(LocalDate.of(2026, 7, 5), "Patos", "Anderson Neiff");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 12), "Pilar", "Anderson Neiff");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 23), "Cajazeiras", "Anderson Neiff");

            FachadaShow.criarShow(LocalDate.of(2026, 6, 12), "Campina Grande", "Ivete Sangalo");
            FachadaShow.criarShow(LocalDate.of(2026, 5, 25), "Pilar", "Ivete Sangalo");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 22), "Cabedelo", "Ivete Sangalo");

            FachadaShow.criarShow(LocalDate.of(2026, 8, 20), "Guarabira", "Eminem");
            FachadaShow.criarShow(LocalDate.of(2026, 9, 10), "João Pessoa", "Eminem");
            FachadaShow.criarShow(LocalDate.of(2026, 9, 15), "Campina Grande", "Eminem");

            FachadaShow.criarShow(LocalDate.of(2026, 8, 18), "Campina Grande", "Sabrina Carpenter");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 16), "Guarabira", "Sabrina Carpenter");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 25), "Patos", "Sabrina Carpenter");

            FachadaShow.criarShow(LocalDate.of(2026, 8, 17), "Campina Grande", "Olivia Rodrigo");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 22), "Sousa", "Olivia Rodrigo");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 29), "Cajazeiras", "Olivia Rodrigo");

            System.out.println("Todos os dados foram semeados com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro durante a inserção dos dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void atualizarFotoArtista(String nomeArtista, String caminhoFoto) {
        try (InputStream input = Cadastrar.class.getResourceAsStream(caminhoFoto)) {
            if (input == null) {
                System.out.println("Aviso: Foto não encontrada para " + nomeArtista + " no caminho " + caminhoFoto);
                return;
            }
            
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            
            byte[] bytesFoto = buffer.toByteArray();
            FachadaArtista.alterarFoto(nomeArtista, bytesFoto);
            
        } catch (Exception e) {
            System.err.println("Não foi possível salvar a foto de " + nomeArtista + ": " + e.getMessage());
        }
    }
}