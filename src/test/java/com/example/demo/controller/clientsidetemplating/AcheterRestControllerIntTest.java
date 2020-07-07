package com.example.demo.controller.clientsidetemplating;

import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.controller.clientsidetemplating.dto.FactureDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Test d'intégration global de l'application, génération de requete http et vérification du retour.
 * Toute les couches sont testées mais de manière superficielles.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcheterRestControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FactureRepository factureRepository;

    /**
     * 1er version  (choix à faire)
     * on manipuple directement le repository pour injecter des articles en base de données.
     * C'est un test dit "boite blanche" (contraire de boite noire) car on va insérer ou charger des
     * données en base de données. Ce qui revient à manipuler une couche plus basse (repositoty) que celle que
     * l'on interroge dans notre test (Controller).
     * C'est souvent plus facile à faire, mais il y a une grande probabilité de devoir faire évoluer le test à
     * la première évolution dans l'application, même mineur. exemple ajouter un champ obligatoire dans Article.
     */
    @Test
    public void acheter_boiteBlanche() {
        Article article = new Article();
        article.setLibelle("article de test");
        article.setPrix(4);
        articleRepository.save(article);
        Long id = article.getId();

        int size = factureRepository.findAll().size();

        // Action d'achat
        AchatDto achatDto = new AchatDto();
        achatDto.setArticleId(id);
        achatDto.setQuantite(1);
        List<AchatDto> achats = singletonList(achatDto);
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/rest/acheter",
                achats,
                String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        int size2 = factureRepository.findAll().size();
        Assertions.assertThat(size2).isEqualTo(size + 1);
    }

    /**
     * 2ieme version : test boite noire
     * on manipule uniquement des controller et non des couches plus basses.
     * Plus complexe à écrire mais moins de risque de devoir faire évoluer le test.
     */
    @Test
    public void acheter_boiteNoire() {
        int nbFactures = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/rest/factures",
                FactureDto[].class).getBody().length;

        Article article = new Article();
        article.setLibelle("article de test");
        article.setPrix(4);
        articleRepository.save(article);
        Long id = article.getId();

        // Action d'achat
        AchatDto achatDto = new AchatDto();
        achatDto.setArticleId(id);
        achatDto.setQuantite(1);
        List<AchatDto> achats = singletonList(achatDto);
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/rest/acheter",
                achats,
                String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        int nbFactures2 = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/rest/factures",
                FactureDto[].class).getBody().length;

        Assertions.assertThat(nbFactures2).isEqualTo(nbFactures + 1);
    }

}
