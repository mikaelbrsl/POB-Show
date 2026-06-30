package requisito;

import java.util.List;

import modelo.Cidade;
import repositorio.Repositorio;
import repositorio.RepositorioCidade;

public class FachadaCidade {

    private static RepositorioCidade repCidade = new RepositorioCidade();

    public static void alterarCidade(String nomeOriginal, String novoNome) throws Exception {

        try {
            Repositorio.conectar();
            Repositorio.begin();
            Cidade cid = repCidade.localizar(nomeOriginal);
            if (cid == null)
                throw new Exception("alterar cidade - cidade inexistente:" + nomeOriginal);

            cid.setNome(novoNome);

            repCidade.atualizar(cid);
            Repositorio.commit();

        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void criarCidade(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Cidade c = repCidade.localizar(nome);
            if (c != null)
                throw new Exception("criar cidade - cidade ja existe:" + nome);

            c = new Cidade(nome);

            repCidade.criar(c);
            Repositorio.commit();

        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static void apagarCidade(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Repositorio.begin();

            Cidade c = repCidade.localizar(nome);
            if (c == null)
                throw new Exception("apagar cidade - cidade não existe:" + nome);

            c = new Cidade(nome);

            repCidade.deletar(c);
            Repositorio.commit();

        } catch (Exception e) {
            Repositorio.rollback();
            throw e;
        } finally {
            Repositorio.desconectar();
        }
    }

    public static List<Cidade> listarCidades() {
        Repositorio.conectar();
        List<Cidade> lista = repCidade.listar();
        Repositorio.desconectar();
        return lista;
    }

    public static Cidade localizarCidade(String nome) throws Exception {
        try {
            Repositorio.conectar();
            Cidade c = repCidade.localizar(nome);
            if (c == null)
                throw new Exception("localizar cidade - cidade não existe:" + nome);
            return c;

        } catch (Exception e) {
            throw e;
        } finally {
            Repositorio.desconectar();

        }
    }

}
