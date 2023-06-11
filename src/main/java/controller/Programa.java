package controller;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Automovel;
import entities.Modelo;
import entities.Marca;

public class Programa {

    public static void main(String[] args) throws ParseException {

        System.out.println("\n*** Versao 1 - Inicial ***");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("atividade");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Marca marca1 = new Marca(null,"Honda");
        Modelo modelo1 = new Modelo(null, "Civic", 100, marca1);
        modelo1.setMarca(marca1);
        Automovel automovel1 = new Automovel(null, 2023, 2023, "Sem observacoes", 90000.0f, 10000, modelo1);
        modelo1.getAutomoveis().add(automovel1);

        em.persist(marca1);
        em.persist(modelo1);
        em.persist(automovel1);

        em.getTransaction().commit();

        Query query1 = em.createQuery("SELECT m FROM Modelo m");

        List<Modelo> modelos = query1.getResultList();
        for (Modelo modelo : modelos) {
            System.out.println("\n *** ["+ modelo.getDescricao() + "] ***");
            Marca marca = modelo.getMarca();
            if (marca != null) {
                System.out.println("\tMarca: " + marca.getNome());
            }
            for (Automovel automovel : modelo.getAutomoveis()) {
                System.out.println("\tAutomovel: " + " Ano: " + automovel.getAnoFabricacao()
                        + " | Preco: " + automovel.getPreco());
            }
        }

        Query query2 = em.createQuery("SELECT a FROM Automovel a");

        List<Automovel> automoveis = query2.getResultList();
        for (Automovel automovel : automoveis) {
            System.out.println("\n *** [" + automovel.getAnoFabricacao()
                    + " | Preco: " + automovel.getPreco() + "] ***");
            Modelo modelo = automovel.getModelo();
            if (modelo != null) {
                System.out.println("\tModelo: " + modelo.getDescricao());
                Marca marca = modelo.getMarca();
                if (marca != null) {
                    System.out.println("\tMarca: " + marca.getNome());
                }
            }
        }

        em.close();
        emf.close();
    }
}

