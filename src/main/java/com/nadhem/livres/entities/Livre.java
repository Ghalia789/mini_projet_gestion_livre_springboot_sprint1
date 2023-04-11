package com.nadhem.livres.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Livre {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idLivre;
	
	private String titreLivre;
	private String auteurLivre;
	private Double prixLivre;
	 private Integer quantiteStock;
	private Date datePublication;
	
	public Livre() {
		super();
	}
	public Livre(String titreLivre, String auteurLivre, Double prixLivre, Integer quantiteStock, Date datePublication) {
		super();
        this.titreLivre = titreLivre;
        this.auteurLivre = auteurLivre;
        this.prixLivre = prixLivre;
        this.quantiteStock = quantiteStock;
        this.datePublication = datePublication;
    }
	public Long getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}

	public String getTitreLivre() {
		return titreLivre;
	}

	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	public String getAuteurLivre() {
		return auteurLivre;
	}

	public void setAuteurLivre(String auteurLivre) {
		this.auteurLivre = auteurLivre;
	}

	public Double getPrixLivre() {
		return prixLivre;
	}

	public void setPrixLivre(Double prixLivre) {
		this.prixLivre = prixLivre;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}
	public Integer getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(Integer quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	
	@Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titreLivre='" + titreLivre + '\'' +
                ", auteurLivre='" + auteurLivre + '\'' +
                ", prixLivre=" + prixLivre +
                ", quantiteStock=" + quantiteStock +
                ", datePublication=" + datePublication +
                '}';
    }
}
