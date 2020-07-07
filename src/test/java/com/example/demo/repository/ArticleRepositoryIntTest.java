package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest  // charge que les Repository et les Entity
public class ArticleRepositoryIntTest {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FactureRepository factureRepository;

    @Test
    public void findTopArticleAchete0Article() {
        List<Article> top = articleRepository.findTopArticleAchete();
        Assertions.assertThat(top).isEmpty();
    }

    // par défaut chaque test est rollbacké
    // @Rollback(false)
    // @Commit
    @Test
    public void findTopArticleAcheteAvecArticles() {
        Article article1 = new Article();
        article1.setPrix(10);
        article1.setLibelle("article1");
        articleRepository.save(article1);

        Article article2 = new Article();
        article2.setPrix(10);
        article2.setLibelle("article2");
        articleRepository.save(article2);

        createFacture(article1, 3);
        createFacture(article2, 2);
        createFacture(article2, 2);

        List<Article> top = articleRepository.findTopArticleAchete();
        Assertions.assertThat(top).hasSize(2);
        Assertions.assertThat(top.get(0).getLibelle()).isEqualTo("article2");
    }

    @Test
    public void findTopArticleAcheteAvecArticles_After() {
        List<Article> top = articleRepository.findTopArticleAchete();
        Assertions.assertThat(top).isEmpty();
    }

    private void createFacture(Article article, int quantite) {
        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setQuantite(quantite);
        ligneFacture.setArticle(article);

        Facture facture = new Facture();
        Set<LigneFacture> ligneFactures = new HashSet<>();
        ligneFactures.add(ligneFacture);
        facture.setLigneFactures(ligneFactures);
        factureRepository.save(facture);
    }
}
