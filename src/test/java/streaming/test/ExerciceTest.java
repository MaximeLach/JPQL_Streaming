/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Assert;

import org.junit.Test;
import static org.junit.Assert.*;
import streaming.entity.Film;

/**
 *
 * @author admin
 */
public class ExerciceTest {
    
    // Vérifier le titre du film d'id 4
    //@Test
    public void Exercice1(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query q = em.createQuery("SELECT f FROM Film f WHERE f.id=4");
        Film f = (Film)q.getSingleResult();
    Assert.assertEquals("Fargo", f.getTitre());
    }
    
    //Vérifier le nombre de films
    //@Test
    public void Exercice3(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long l = (long) em.createQuery("SELECT COUNT(f) From Film f").getSingleResult();
        Assert.assertEquals(4, l);
    }
    
    //Année de prod mini de nos films
    //@Test
    public void Exercice5(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        int a = (Integer) em.createQuery("SELECT MIN(f.annee) FROM Film f").getSingleResult();
        Assert.assertEquals(1968, a);
    }
    
    //Nombre de liens du film 'Big Lebowski'
    //@Test
    public void Exercice7(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT(l) FROM Film f JOIN f.liens l WHERE f.titre LIKE '%Big Lebowski%'").getSingleResult();
        Assert.assertEquals(1, n);
    }
    
    //Nombre de films réalisés par Polanski
    //@Test
    public void Exercice9(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT(r) FROM Personne p JOIN p.filmsRealises r WHERE p.nom LIKE '%Polanski%'").getSingleResult();
        Assert.assertEquals(2, n);
    }
    
    //Nombre de films interprétés par Polanski
    //@Test
    public void Exercice11(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (j) FROM Personne p JOIN p.filmsJoues j WHERE p.nom LIKE '%Polanski%'").getSingleResult();
        Assert.assertEquals(1, n);
    }
    
    //Nombre de films à la fois interprétés et réalisés par polanski
    //@Test
    public void Exercice13(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.acteurs a JOIN f.realisateurs r WHERE a.nom='Polanski' AND r.nom='Polanski'").getSingleResult();
        Assert.assertEquals(1, n);
    }
    
    //Le titre du film d'horreur anglais réalisé par roman polanski
    //@Test
    public void Exercice15(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.pays p JOIN f.realisateurs r JOIN f.genre g WHERE p.nom='UK' AND r.nom='Polanski' AND r.prenom='Roman' AND g.nom='Horreur'").getSingleResult();
        Assert.assertEquals(1, n);
    }
    
    //Le nombre de films réalisés par joel coen
    //@Test
    public void Exercice17(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT(r) FROM Personne p JOIN p.filmsRealises r WHERE p.nom LIKE '%Coen%' AND p.prenom LIKE '%Joel%'").getSingleResult();
        Assert.assertEquals(2, n);    
    }
    
    //Le nombre de films réalisés par les 2 frères coen
    //@Test
    public void Exercice19(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r WHERE r.nom LIKE '%Coen%'").getSingleResult();
        Assert.assertEquals(4, n);  
    }
    
    //Le nombre de films des frères coen interprétés par Steve Buscemi    
    //@Test
    public void Exercice21(){
        EntityManager em=Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        List<Film> films = em.createQuery(""
                + "SELECT f FROM Film f JOIN f.realisateurs r WHERE r.nom='Coen' AND r.prenom='Joel' "
                + "INTERSECT "
                + "SELECT f FROM Film f JOIN f.realisateurs r WHERE r.nom='Coen' AND r.prenom='Ethan' "
                + "INTERSECT "
                + "SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom='Buscemi' AND a.prenom='Steve'").getResultList();
        Assert.assertEquals(2, films.size());
        
//        long n = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r1 JOIN f.realisateurs r2 JOIN f.acteurs a "
//                                    + "WHERE r1.nom = 'Coen' AND r1.prenom='Joel' "
//                                    + "AND r2.nom = 'Coen' AND r2.prenom='Ethan' "
//                                    + "AND a.nom='Buscemi'").getSingleResult();
//        Assert.assertEquals(2, n);    
        
    }
    
    //Le nombre de films policiers des frères Coen interprétés par Steve Buscemi
    //@Test
    public void Exercice23(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT(f) FROM Film f JOIN f.realisateurs r JOIN f.acteurs a JOIN f.genre g WHERE g.nom ='Policier' AND r.prenom ='Ethan' AND r.nom ='Coen' AND a.nom='Buscemi'"
                + "UNION SELECT COUNT(f) FROM Film f JOIN f.realisateurs r JOIN f.acteurs a JOIN f.genre g WHERE g.nom ='Policier' AND r.prenom ='Joel' AND r.nom ='Coen' AND a.nom='Buscemi'").getSingleResult();
        Assert.assertEquals(1, n);
    }
    
    //Le nombre de saisons de la série Dexter
    //@Test
    public void Exercice25(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (n) FROM Serie s JOIN s.saisons n WHERE s.titre = 'Dexter'").getSingleResult();
        Assert.assertEquals(8,n);
    }
    
    //Le nombre d'épisodes de la saison 8 de la série Dexter
    //@Test
    public void Exercice27(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (e) FROM Episode e WHERE e.saison.serie.titre = 'Dexter' AND e.saison.id='8'").getSingleResult();
        Assert.assertEquals(12,n);
    }
    
    //Le nombre total d'épisodes de la série Dexter
    //@Test
    public void Exercice29(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (e) FROM Serie s JOIN s.saisons z JOIN z.episodes e WHERE s.titre = 'Dexter'").getSingleResult();
        Assert.assertEquals(96,n);
    }
    
    //Le nombre total de liens pour nos films policiers américains
    //@Test
    public void Exercice31(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (l) FROM Film f JOIN f.liens l JOIN f.genre g JOIN f.pays p WHERE p.nom='USA' AND g.nom = 'Policier'").getSingleResult();
        Assert.assertEquals(3,n);
    }
    
    //Le nombre totals de liens pour nos films d'horreur interprétés par Polanski
    //@Test
    public void Exercice33(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        long n = (long) em.createQuery("SELECT COUNT (l) FROM Film f JOIN f.liens l JOIN f.genre g JOIN f.acteurs a WHERE a.nom='Polanski' AND a.prenom ='Roman' AND g.nom = 'Horreur'").getSingleResult();
        Assert.assertEquals(1,n);
    }
    
    //Tous les films d'horreur, sauf ceux interprétés par Polanski (utiliser UNION ou MINUS ou INTERSECT )
    @Test
    public void Exercice35(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        List<Film> films = em.createQuery(""
                + "SELECT f FROM Film f JOIN f.genre g WHERE g.nom='Horreur' "
                + "INTERSECT "
                + "SELECT f FROM Film f WHERE NOT EXISTS(SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom ='Polanski')").getResultList();
        Assert.assertEquals(0,films.size());
    }
    
    //Parmi tous les films, uniquement ceux interprétés par Polanski  ( utiliser UNION ou MINUS ou INTERSECT )
    //@Test
    public void Exercice37(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        List<Film> films = em.createQuery(""
                + "SELECT f FROM Film f "
                + "INTERSECT "
                + "SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getResultList();
        Assert.assertEquals(1,films.size());
    }
    
    //Tous les films interprétés par Polanski et aussi tous les films d'horreur ( utiliser UNION ou MINUS ou INTERSECT )
    //@Test
    public void Exercice39(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        List <Film> films = em.createQuery(""
                + "SELECT f FROM Film f JOIN f.genre g WHERE g.nom='Horreur' "
                + "UNION "
                + "SELECT f FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski'").getResultList();
        Assert.assertEquals(1,films.size());
    }
}
