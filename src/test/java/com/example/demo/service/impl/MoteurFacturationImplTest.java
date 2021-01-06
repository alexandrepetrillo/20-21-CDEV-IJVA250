package com.example.demo.service.impl;

import com.example.demo.dto.AchatDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.impl.fake.ArticleRepositoryFake;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Test unitaire de service
public class MoteurFacturationImplTest {

    @Test
    public void creerFactureAvecUnArticleAchete() {
        // on va appeler la méthode à tester
        MoteurFacturationImpl moteurFacturationImpl = new MoteurFacturationImpl(
                factureRepositoryMock, new ArticleRepositoryFake()
        );

        // 1 achat
        List<AchatDto> achats = new ArrayList<>();
        AchatDto achat = new AchatDto();
        achat.setQuantite(2);
        achat.setArticleId(999);
        achats.add(achat);
        Facture facture = moteurFacturationImpl.facturer(achats, new Client());

        // on vérifie le résultat
        assertThat(facture.getLigneFactures().size()).isEqualTo(1);
        LigneFacture firstLigneFacture = facture.getLigneFactures().iterator().next();
        assertThat(firstLigneFacture.getQuantite()).isEqualTo(2);
    }

    // possiblité d'utiliser les annotations Mockito (@Mock)
    FactureRepository factureRepositoryMock = mock(FactureRepository.class);
    ArticleRepository articleRepositoryMock = mock(ArticleRepository.class);

    // on va appeler la méthode à tester
    // possiblité d'utiliser l'annotation Mockito (@InjectMock)
    MoteurFacturationImpl moteurFacturationImpl = new MoteurFacturationImpl(factureRepositoryMock, articleRepositoryMock);

    @Test
    public void creerFactureAvecUnArticleAcheteAvecMock() {
        Client client = new Client();

        Article article = new Article();
        article.setId(999L);
        article.setPrix(10);
        when(articleRepositoryMock.findById(999L)).thenReturn(Optional.of(article));

        // 1 achat
        List<AchatDto> achats = new ArrayList<>();
        AchatDto achat = new AchatDto();
        achat.setQuantite(2);
        achat.setArticleId(999);
        achats.add(achat);

        Facture facture = moteurFacturationImpl.facturer(achats, client);
        // on vérifie le résultat
        assertThat(facture.getClient()).isEqualTo(client);
        assertThat(facture.getTotal()).isEqualTo(20);
        LigneFacture firstLigneFacture = facture.getLigneFactures().iterator().next();
        assertThat(firstLigneFacture.getQuantite()).isEqualTo(2);
        assertThat(firstLigneFacture.getArticle().getId()).isEqualTo(999);

        // possibilité d'utiliser les arguments captor pour vérifier plus précisement le paramètre qui a été donné
        verify(factureRepositoryMock).save(any());
    }

}
