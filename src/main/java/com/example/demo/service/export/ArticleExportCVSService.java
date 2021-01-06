package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ArticleExportCVSService {

    @Autowired
    private ArticleRepository articleRepository;

    public void export(PrintWriter writer) {
        writer.println("Libelle;Prix");
        List<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            String line = article.getLibelle() + ";" + article.getPrix();
            writer.println(line);
        }
    }


}
