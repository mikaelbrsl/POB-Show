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
            System.out.println("Cadastrando cidades...");
            FachadaCidade.criarCidade("João Pessoa");
            FachadaCidade.criarCidade("Campina Grande");
            FachadaCidade.criarCidade("Recife");
            FachadaCidade.criarCidade("Natal");
            FachadaCidade.criarCidade("Fortaleza");

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
            atualizarFotoArtista("Ivete Sangalo", "/fotos/ivete_sangalo.jpg");
            atualizarFotoArtista("Eminem", "/fotos/eminem.jpg");
            atualizarFotoArtista("Sabrina Carpenter", "/fotos/sabrina_carpenter.jpg");
            atualizarFotoArtista("Olivia Rodrigo", "/fotos/olivia_rodrigo.jpg");

            System.out.println("Cadastrando shows...");
            FachadaShow.criarShow(LocalDate.of(2026, 5, 20), "João Pessoa", "Alok");
            FachadaShow.criarShow(LocalDate.of(2026, 5, 21), "João Pessoa", "Anitta");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 10), "Campina Grande", "Alok");
            FachadaShow.criarShow(LocalDate.of(2026, 6, 12), "Campina Grande", "Ivete Sangalo");
            FachadaShow.criarShow(LocalDate.of(2026, 7, 5), "Recife", "Anderson Neiff");
            FachadaShow.criarShow(LocalDate.of(2026, 7, 5), "João Pessoa", "Anitta");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 15), "Natal", "Ivete Sangalo");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 16), "Natal", "Anderson Neiff");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 17), "Campina Grande", "Olivia Rodrigo");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 18), "Campina Grande", "Sabrina Carpenter");
            FachadaShow.criarShow(LocalDate.of(2026, 8, 19), "Natal", "Eminem");

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