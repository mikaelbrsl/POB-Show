package modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "artista20241370035")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nomeArtistico;

    @OneToMany(mappedBy = "artista",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Show> listaDeShow;

    public Artista() {
    }

    public Artista(String nomeArstitico, List<Show> listaDeShow) {
        this.nomeArtistico = nomeArstitico;
        this.listaDeShow = listaDeShow;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public List<Show> getListaDeShow() {
        return listaDeShow;
    }

    public void setListaDeShow(List<Show> listaDeShow) {
        this.listaDeShow = listaDeShow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void adicionar(Show show) {
        listaDeShow.add(show);
    }

    public void remover(Show show) {
        listaDeShow.remove(show);

    }

    @Override
    public String toString() {

        if (listaDeShow.isEmpty()) {
            return "Sem Shows";
        }


        boolean primeiroShowAdicionado = true;
        StringBuilder texto = new StringBuilder();
        texto.append("Nome: ")
            .append(nomeArtistico)
            .append("| Shows agendados: ");

        for (Show s : listaDeShow) {
        if (s != null) {
            if (!primeiroShowAdicionado) {
                texto.append(", ");
            }

            texto.append("[Id: ")
                .append(s.getId())
                .append(", Cidade: ").append(s.getCidade().getNome())
                .append(", Data: ").append(s.getData())
                .append("]");
            
            primeiroShowAdicionado = false;
        }
    }
        return texto.toString();
    }
}
