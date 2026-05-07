package modelo;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity(name="cidade20241370035")
public class Cidade{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private String nome;

    @OneToMany(mappedBy = "cidade", 
    cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
    orphanRemoval=true
    )
    private List<Show> listaDeShow;

    public Cidade () {}
    
    public Cidade(String nome, List<Show> listaDeShow) {
        this.nome = nome;
        this.listaDeShow = listaDeShow;
    }

//    public void addShow(Show show){
//        listaDeShow.add(show);
//    }
//
//    public void removeShow(Show show){
//        if(listaDeShow.contains(show)){
//            listaDeShow.remove(show);
//        } else{
//            throw new NullPointerException("Não tem esse show na cidade");
//        }
//        
//    }
    
    public void adicionar(Show show) {
    	listaDeShow.add(show);
    	
    }
    
    public void remover(Show show) {
    	listaDeShow.remove(show);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
    	String texto = "Nome: "+nome + " | Shows agendados: "; 
    	if(listaDeShow.isEmpty())
    		texto += "Sem Shows";
    	else
    		for (Show s : listaDeShow)
    			if(s != null)
    				texto += +s.getId() + ", ";
    	
    	return texto;
    }
}