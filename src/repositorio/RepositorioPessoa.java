/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranhão Ayres
 **********************************/
package repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.TypedQuery;
import modelo.Pessoa;
import util.Util;

public class RepositorioPessoa extends Repositorio<Pessoa> {
	@PostPersist
	@PostUpdate
	@PostLoad
	public void calcularIdade(Pessoa p) throws Exception{
		try {
			LocalDate data1 = LocalDate.parse(p.getDtNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate data2 = LocalDate.now();
			long anos = ChronoUnit.YEARS.between(data1, data2);
			p.setIdade((int) anos); // atributo calculado
			System.out.println("---trigger: calcularIdade =" + p.getNome()+p.getIdade());
		} catch (Exception e) {
			throw new Exception(" calculo da idade " + e.getMessage());
		}
	}

	public Pessoa localizar(Object chave) {
		String nome = (String) chave;
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select p from Pessoa p
				LEFT JOIN FETCH p.apelidos
				LEFT JOIN FETCH p.telefones
				where p.nome=:n
				 """, Pessoa.class);
		q.setParameter("n", nome);

		return q.getSingleResultOrNull();
	}

	public List<Pessoa> listar() {
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select  p from Pessoa p
				order by p.id
				""", Pessoa.class);

		return q.getResultList();
	}

	public List<Pessoa> listar(String caracteres) {
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select p from Pessoa p where p.nome like :x
				""", Pessoa.class);
		q.setParameter("x", "%" + caracteres + "%");

		return q.getResultList();
	}

	public List<Pessoa> listarPorNTelefones(int n) {
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select p from Pessoa p
				where SIZE(p.telefones) = :x
				""", Pessoa.class);
		q.setParameter("x", n);

		return q.getResultList();
	}

	public List<Pessoa> listarPorMes(String mes) {
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select p from Pessoa p
				where extract(month from p.dtnascimento) = :m
				""", Pessoa.class);
		q.setParameter("m", Integer.parseInt(mes));

		return q.getResultList();
	}

	public boolean temTelefoneCelular(String nome) {
		TypedQuery<Long> q = Util.getManager().createQuery("""
				select count(t) from Pessoa p
				join p.telefones t
				where p.nome = :x and t.numero like :y
				""", Long.class);
		q.setParameter("x", nome);
		q.setParameter("y", "9%");
		Long cont = q.getSingleResultOrNull();
		return cont > 0;
	}

	public boolean temTelefoneFixo(String nome) {
		TypedQuery<Long> q = Util.getManager().createQuery("""
				select count(t) from Pessoa p
				join p.telefones t
				where p.nome = :x and t.numero like :y
				""", Long.class);
		q.setParameter("x", nome);
		q.setParameter("y", "3%");
		Long cont = q.getSingleResultOrNull();
		return cont > 0;
	}

	public List<Pessoa> listarPorApelido(String apelido) {
		TypedQuery<Pessoa> q = Util.getManager().createQuery("""
				select p from Pessoa p
				LEFT JOIN FETCH p.apelidos a
				where a like :x
				""", Pessoa.class);
		q.setParameter("x", "%" + apelido + "%");

		return q.getResultList();
	}

}