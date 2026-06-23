package repositorio;

import jakarta.persistence.TypedQuery;
import modelo.Artista;
import modelo.Cidade;
import modelo.Show;
import util.Util;

import java.util.List;

public class RepositorioShow extends Repositorio<Show> {
    @Override
    public List<Show> listar(){
        TypedQuery<Show> q = Util.getManager().createQuery("select s from Show s order by s.id", Show.class);
        return q.getResultList();
    }

    @Override
    public Show localizar(Object chave){
        int id = (int) chave;

        TypedQuery<Show> q = Util.getManager().createQuery("select s from Show s where s.id= :id", Show.class);

        return q.setParameter("id", id).getSingleResultOrNull();

    }
}
