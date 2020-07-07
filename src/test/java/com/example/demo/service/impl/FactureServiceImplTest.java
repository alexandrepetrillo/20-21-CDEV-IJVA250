package com.example.demo.service.impl;

import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.impl.fake.ArticleRepositoryFake;
import com.example.demo.service.impl.fake.ClientServiceFake;
import com.example.demo.service.impl.fake.FactureRepositoryFake;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Test unitaire de service
public class FactureServiceImplTest {

    @Test
    public void creerFactureAvecUnArticleAchete() {
        // on va appeler la méthode à tester
        FactureServiceImpl factureServiceImpl = new FactureServiceImpl(
                new FactureRepositoryFake(),
                new ArticleRepositoryFake(),
                new ClientServiceFake()
        );

        // 1 achat
        List<AchatDto> achats = new ArrayList<>();
        AchatDto achat = new AchatDto();
        achat.setQuantite(2);
        achat.setArticleId(999);
        achats.add(achat);
        Facture facture = factureServiceImpl.creerFacture(achats);

        // on vérifie le résultat
        assertThat(facture.getLigneFactures().size()).isEqualTo(1);
        LigneFacture firstLigneFacture = facture.getLigneFactures().iterator().next();
        assertThat(firstLigneFacture.getQuantite()).isEqualTo(2);
    }


    // possiblité d'utiliser les annotations Mockito (@Mock)
    FactureRepository factureRepository = mock(FactureRepository.class);
    ArticleRepository articleRepository  = mock(ArticleRepository.class);
    ClientService clientService = mock(ClientService.class);

    @Test
    public void creerFactureAvecUnArticleAcheteAvecMock() {
        Client client = new Client();
        when(clientService.findAllClients()).thenReturn(singletonList(client));

        Article article = new Article();
        article.setId(999L);
        when(articleRepository.findById(999L)).thenReturn(Optional.of(article));

        // on va appeler la méthode à tester
        // possiblité d'utiliser l'annotation Mockito (@InjectMock)
        FactureServiceImpl factureServiceImpl = new FactureServiceImpl(
                factureRepository,
                articleRepository,
                clientService
        );

        // 1 achat
        List<AchatDto> achats = new ArrayList<>();
        AchatDto achat = new AchatDto();
        achat.setQuantite(2);
        achat.setArticleId(999);
        achats.add(achat);
        Facture facture = factureServiceImpl.creerFacture(achats);

        // on vérifie le résultat
        assertThat(facture.getLigneFactures().size()).isEqualTo(1);
        LigneFacture firstLigneFacture = facture.getLigneFactures().iterator().next();
        assertThat(firstLigneFacture.getQuantite()).isEqualTo(2);
        assertThat(firstLigneFacture.getArticle().getId()).isEqualTo(999);

        // possibilité d'utiliser les arguments captor pour vérifier plus précisement le paramètre qui a été donné
        verify(factureRepository).save(any());
    }

}
