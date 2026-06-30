package repositorio;

import jakarta.persistence.TypedQuery;
import modelo.Cidade;
import util.Util;

import java.util.List;

public class RepositorioCidade extends Repositorio<Cidade> {

    @Override
    public List<Cidade> listar() {
        TypedQuery<Cidade> q = Util.getManager().createQuery(
            "select distinct c from Cidade c " +
            "left join fetch c.listaDeShow s " +
            "left join fetch s.artista " +
            "order by c.id", Cidade.class);
        return q.getResultList();
    }

    @Override
    public Cidade localizar(Object chave) {
        String nome = (String) chave;
        TypedQuery<Cidade> q = Util.getManager().createQuery(
            "select c from Cidade c " +
            "left join fetch c.listaDeShow s " +
            "left join fetch s.artista " +
            "where c.nome = :nome", Cidade.class);

        return q.setParameter("nome", nome).getSingleResultOrNull();
    }
}