package requisito;

import java.util.List;

import modelo.Artista;
import repositorio.Repositorio;
import repositorio.RepositorioArtista;


public class FachadaArtista {

    private static RepositorioArtista repArtista = new RepositorioArtista();

    public static void alterarFoto(String nomeArtista, byte[] bytesfoto) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Artista a = repArtista.localizar(nomeArtista);
            if (a == null) {
                throw new Exception("alterar foto - artista não encontrado:" + nomeArtista);
            }
            a.setFoto(bytesfoto);
            repArtista.atualizar(a);
            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void alterarArtista(String nomeOriginal, String novoNome) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Artista a = repArtista.localizar(nomeOriginal);
            if (a == null) {
                throw new Exception("alterar artista - artista não encontrado:" + nomeOriginal);
            }
            a.setNomeArtistico(novoNome);
            ;
            repArtista.atualizar(a);
            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void criarArtista(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Artista a = repArtista.localizar(nome);
            if (a != null) {
                throw new Exception("criar artista - artista ja existe:" + nome);
            }

            a = new Artista(nome);
            repArtista.criar(a);
            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static List<Artista> listarArtistas() {
        Repositorio.conectar();
        List<Artista> lista = repArtista.listar();
        Repositorio.desconectar();
        return lista;
    }

    public static Artista localizarArtista(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Artista a = repArtista.localizar(nome);
            if (a == null) {
                throw new Exception("localizar artista - artista não existe:" + nome);
            }
            return a;
        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void apagarArtista(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Artista a = repArtista.localizar(nome);
            if (a == null) {
                throw new Exception("apagar artista - artista não encontrado: " + nome);
            }
            repArtista.deletar(a);
            Repositorio.commit();
        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
            ;
        }
    }
}
