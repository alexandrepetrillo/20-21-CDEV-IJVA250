package com.example.demo.service.impl;

import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.ClientService;
import com.example.demo.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Service contenant les actions métiers liées aux factures.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private FactureRepository factureRepository;
    private ArticleRepository articleRepository;
    private ClientService clientService;

    @Autowired
    public FactureServiceImpl(
            FactureRepository factureRepository,
            ArticleRepository articleRepository,
            ClientService clientService
            ) {
        this.factureRepository = factureRepository;
        this.articleRepository = articleRepository;
        this.clientService = clientService;
    }

    @Override
    public List<Facture> findAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return factureRepository.findById(id).get();
    }

    @Override
    public Facture creerFacture(List<AchatDto> achats) {
        Client client = getClientConnecte();

        Facture facture = new Facture();
        facture.setClient(client);
        Set<LigneFacture> lignesFactures = new HashSet<>();
        for (AchatDto achat : achats) {
            LigneFacture ligneFacture = new LigneFacture();
            long articleId = achat.getArticleId();
            Article article = articleRepository.findById(articleId).get();
            ligneFacture.setArticle(article);
            ligneFacture.setQuantite(achat.getQuantite());
            ligneFacture.setFacture(facture);
            lignesFactures.add(ligneFacture);
        }
        facture.setLigneFactures(lignesFactures);
        factureRepository.save(facture);
        return facture;
    }

    private Client getClientConnecte() {
        // Code en dur pour simplifier
        List<Client> allClients = clientService.findAllClients();
        int iRandom = new Random().nextInt(allClients.size());
        return allClients.get(iRandom);
    }
}
