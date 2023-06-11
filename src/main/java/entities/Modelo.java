package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	private Integer potencia;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    
	@OneToMany
	private List<Automovel> automoveis = new ArrayList<>();

	public Modelo() {
	}

	public Modelo(Integer id, String descricao, Integer potencia, Marca marca) {
		this.id = id;
		this.descricao = descricao;
		this.potencia = potencia;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public List<Automovel> getAutomoveis() {
		return automoveis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(automoveis, descricao, id, potencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		return Objects.equals(automoveis, other.automoveis) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(potencia, other.potencia);
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", descricao=" + descricao + ", potencia=" + potencia + ", marca="
				+ marca + ", automoveis=" + automoveis + "]";
	}
}
