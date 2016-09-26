/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ETY
 */
@Entity
public class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    
    @ManyToMany(mappedBy = "realisateurs")
    private List<Film> filmsRealises = new ArrayList<>();
    @ManyToMany(mappedBy = "acteurs")
    private List<Film> filmsJoues = new ArrayList<>();
    @ManyToMany(mappedBy = "realisateurs")
    private List<Serie> seriesRealises = new ArrayList<>();
    @ManyToMany(mappedBy = "acteurs")
    private List<Serie> seriesJoues = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Film> getFilmsRealises() {
        return filmsRealises;
    }

    public void setFilmsRealises(List<Film> filmsRealises) {
        this.filmsRealises = filmsRealises;
    }

    public List<Film> getFilmsJoues() {
        return filmsJoues;
    }

    public void setFilmsJoues(List<Film> filmsJoues) {
        this.filmsJoues = filmsJoues;
    }

    public List<Serie> getSeriesRealises() {
        return seriesRealises;
    }

    public void setSeriesRealises(List<Serie> seriesRealises) {
        this.seriesRealises = seriesRealises;
    }

    public List<Serie> getSeriesJoues() {
        return seriesJoues;
    }

    public void setSeriesJoues(List<Serie> seriesJoues) {
        this.seriesJoues = seriesJoues;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpql.entity.Personne[ id=" + id + " ]";
    }
    
}
