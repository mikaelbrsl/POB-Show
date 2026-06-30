package repositorio;

import jakarta.persistence.TypedQuery;
import modelo.Artista;
import modelo.Show;
import util.Util;

import java.time.LocalDate;
import java.util.List;

public class RepositorioShow extends Repositorio<Show> {
    @Override
    public List<Show> listar() {
        TypedQuery<Show> q = Util.getManager().createQuery(
                "select s from Show s left join fetch s.cidade left join fetch s.artista order by s.id", Show.class);
        return q.getResultList();
    }

    @Override
    public Show localizar(Object chave) {
        int id = (int) chave;
        TypedQuery<Show> q = Util.getManager().createQuery(
                "select s from Show s left join fetch s.cidade left join fetch s.artista where s.id = :id", Show.class);
        return q.setParameter("id", id).getSingleResultOrNull();
    }

    public List<Show> consultarShowsNaData(LocalDate dataBusca) {
        TypedQuery<Show> q = Util.getManager().createQuery(
                "select s from Show s left join fetch s.cidade left join fetch s.artista " +
                        "where s.data = :data",
                Show.class);
        return q.setParameter("data", dataBusca).getResultList();
    }

    public List<Artista> consultarArtistasNaCidade(String nomeCidade) {
        TypedQuery<Artista> q = Util.getManager().createQuery(
                "select distinct a from Show s " +
                        "join s.artista a " +
                        "left join fetch a.listaDeShow " +
                        "where lower(s.cidade.nome) = lower(:nomeCidade)",
                Artista.class);
        return q.setParameter("nomeCidade", nomeCidade).getResultList();
    }

    public List<Artista> consultarArtistasComMaisDeNShowsNaCidade(String nomeCidade, int quantidade) {
        TypedQuery<Artista> q = Util.getManager().createQuery(
                "select s.artista from Show s " +
                        "where lower(s.cidade.nome) = lower(:nomeCidade) " +
                        "group by s.artista " +
                        "having count(s) > :quantidade",
                Artista.class);

        q.setParameter("nomeCidade", nomeCidade);
        q.setParameter("quantidade", (long) quantidade);

        return q.getResultList();
    }

}
