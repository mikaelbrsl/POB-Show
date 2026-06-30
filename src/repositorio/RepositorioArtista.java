package repositorio;

import jakarta.persistence.TypedQuery;
import modelo.Artista;
import util.Util;

import java.util.List;

public class RepositorioArtista extends Repositorio<Artista> {
    @Override
    public List<Artista> listar(){
       TypedQuery<Artista> q = Util.getManager().createQuery(
            "select distinct a from Artista a left join fetch a.listaDeShow order by a.id", Artista.class);
        return q.getResultList();
    }

    @Override
    public Artista localizar(Object chave){
        String nome = (String) chave;

        TypedQuery<Artista> q = Util.getManager().createQuery(
            "select a from Artista a left join fetch a.listaDeShow WHERE a.nomeArtistico = :nome", Artista.class);

        return q.setParameter("nome", nome).getSingleResultOrNull();

    }

}
