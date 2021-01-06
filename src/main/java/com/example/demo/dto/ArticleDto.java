package com.example.demo.dto;

/**
 * Classe permettant d'exposer des données au format JSON au client.
 */
public class ArticleDto {
    private Long id;
    private String libelle;
    private double prix;
    private int stock;

    public ArticleDto() {

    }

    public ArticleDto(Long id, String libelle, double prix, int stock) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
