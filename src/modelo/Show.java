package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "show20241370035")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cidade_fk", nullable = false)
    private Cidade cidade;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artista_fk", nullable = false)
    private Artista artista;

    public Show() {
    }

    public Show(LocalDate data, Cidade cidade, Artista artista) {
        this.data = data;
        this.cidade = cidade;
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void adicionar(Artista a) {
        artista = a;
        a.adicionar(this);

    }

    public void remover(Artista a) {
        artista = null;
        a.remover(this);
    }

    public void adicionar(Cidade c) {
        cidade = c;
        c.adicionar(this);
    }

    public void remover(Cidade c) {
        cidade = null;
        c.remover(this);
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        String texto = "Show [ID=" + id + "] | Data: " + data + " | ";

        if (artista == null) {
            texto += "Sem artista";
        } else {
            texto += "Artista: " + artista.getNomeArtistico();
        }

        texto += " | ";

        if (cidade == null) {
            texto += "Sem cidade";
        } else {
            texto += "Cidade: " + cidade.getNome();
        }

        return texto;
    }

}
