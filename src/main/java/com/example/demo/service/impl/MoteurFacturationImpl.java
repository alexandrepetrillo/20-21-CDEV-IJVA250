package com.example.demo.service.impl;

import com.example.demo.dto.AchatDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.MoteurFacturation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MoteurFacturationImpl implements MoteurFacturation {

    private FactureRepository factureRepository;
    private ArticleRepository articleRepository;

    public MoteurFacturationImpl(FactureRepository factureRepository, ArticleRepository articleRepository) {
        this.factureRepository = factureRepository;
        this.articleRepository = articleRepository;
    }

    public Facture facturer(List<AchatDto> achats, Client client) {
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

}
