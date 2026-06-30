package requisito;

import java.time.LocalDate;
import java.util.List;

import modelo.Artista;
import modelo.Cidade;
import modelo.Show;
import repositorio.Repositorio;
import repositorio.RepositorioArtista;
import repositorio.RepositorioCidade;
import repositorio.RepositorioShow;

public class FachadaShow {

    private static RepositorioShow repShow = new RepositorioShow();
    private static RepositorioCidade repCidade = new RepositorioCidade();
    private static RepositorioArtista repArtista = new RepositorioArtista();

    public static void criarShow(LocalDate dataParsed, String nomeCidade, String nomeArtista) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Cidade cidade = repCidade.localizar(nomeCidade);
            if (cidade == null) {
                throw new Exception("criar show - cidade não encontrada: " + nomeCidade);
            }

            Artista artista = repArtista.localizar(nomeArtista);
            if (artista == null) {
                throw new Exception("criar show - artista não encontrado: " + nomeArtista);
            }

            Show show = new Show(dataParsed, cidade, artista);

            artista.adicionar(show);
            cidade.adicionar(show);

            repShow.criar(show);
            repArtista.atualizar(artista);
            repCidade.atualizar(cidade);

            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void alterarShow(int id, LocalDate dataParsed, String nomeCidade, String nomeArtista)
            throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Show show = repShow.localizar(id);
            if (show == null) {
                throw new Exception("alterar show - show não encontrado com ID: " + id);
            }

            Cidade novaCidade = repCidade.localizar(nomeCidade);
            if (novaCidade == null) {
                throw new Exception("alterar show - cidade não encontrada: " + nomeCidade);
            }

            Artista novoArtista = repArtista.localizar(nomeArtista);
            if (novoArtista == null) {
                throw new Exception("alterar show - artista não encontrado: " + nomeArtista);
            }

            show.getArtista().remover(show);
            show.getCidade().remover(show);

            show.setData(dataParsed);
            show.setCidade(novaCidade);
            show.setArtista(novoArtista);

            novoArtista.adicionar(show);
            novaCidade.adicionar(show);

            repShow.atualizar(show);

            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void apagarShow(int id) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Show show = repShow.localizar(id);
            if (show == null) {
                throw new Exception("apagar show - show não encontrado com ID: " + id);
            }

            Artista artista = show.getArtista();
            Cidade cidade = show.getCidade();

            if (artista != null) {
                artista.remover(show);
                repArtista.atualizar(artista);
            }
            if (cidade != null) {
                cidade.remover(show);
                repCidade.atualizar(cidade);
            }

            repShow.deletar(show);

            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static List<Show> listarShows() {
        Repositorio.conectar();
        List<Show> lista = repShow.listar();
        Repositorio.desconectar();
        return lista;
    }

    public static Show localizarShow(int id) throws Exception {
        try {
            Repositorio.conectar();
            Show show = repShow.localizar(id);
            if (show == null) {
                throw new Exception("localizar show - show não encontrado com ID: " + id);
            }
            return show;
        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    // --- Métodos de Consulta do Projeto ---

    public static List<Artista> consultarArtistasNaCidade(String cidade) throws Exception {
        try {
            Repositorio.conectar();
            return repShow.consultarArtistasNaCidade(cidade);
        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static List<Artista> consultarArtistasComMaisDeNShowsNaCidade(String cid, int quantidade) throws Exception {
        try {
            Repositorio.conectar();
            return repShow.consultarArtistasComMaisDeNShowsNaCidade(cid, quantidade);
        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }
    

    public static List<Show> consultarShowsNaData(LocalDate dataBusca) throws Exception {
        try {
            Repositorio.conectar();
            List<Show> lista = repShow.consultarShowsNaData(dataBusca);
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }
}