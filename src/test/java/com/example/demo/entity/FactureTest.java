package com.example.demo.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test unitaire sur les entity
 */
public class FactureTest {

    @Test
    public void getTotalFactureVide() {
        Facture facture = new Facture();
        Set<LigneFacture> ligneFactures = new HashSet<>();
        facture.setLigneFactures(ligneFactures);
        Assertions.assertThat(facture.getTotal()).isEqualTo(0);
    }

    @Test
    public void getTotalFactureUnArticleQuanite2() {
        Article article = new Article();
        article.setPrix(10);

        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setQuantite(2);
        ligneFacture.setArticle(article);

        Facture facture = new Facture();
        Set<LigneFacture> ligneFactures = new HashSet<>();
        ligneFactures.add(ligneFacture);
        facture.setLigneFactures(ligneFactures);

        Assertions.assertThat(facture.getTotal()).isEqualTo(20);
    }

    // TODO faire un test avec 2 articles différents
}
