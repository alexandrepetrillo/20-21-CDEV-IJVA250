package com.example.demo.controller.clientsidetemplating;

import com.example.demo.DemoApplication;
import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.controller.clientsidetemplating.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.FactureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.MOCK,
        classes = DemoApplication.class) // charge toute l'application (y compris InitData)
@AutoConfigureMockMvc
public class AcheterRestController2Test {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Test
    public void listArticle() throws Exception {
        // Ce qui va pas :
        // pré suppose que l'article "Chargeurs de téléphones Portables" existe.

        ResultActions result = mvc.perform(get("/rest/articles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].libelle", hasItem("Chargeurs de téléphones Portables")));
        String responseAsString = result.andReturn().getResponse().getContentAsString();
        System.out.println(responseAsString);
    }

    @Test
    public void acheter() throws Exception {
        // ce qui va pas
        // on manipuple directement un repository dans un test qui devrait manipuler seulement les controlleurs
        Article article = new Article();
        article.setLibelle("article de test");
        article.setPrix(4);
        articleRepository.save(article);
        Long id = article.getId();

        int size = factureRepository.findAll().size();

        mvc.perform(post("/rest/acheter")
                .content("[{\"articleId\":" + id + ", \"quantite\":1}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int size2 = factureRepository.findAll().size();
        Assertions.assertThat(size2).isEqualTo(size + 1);
    }


    @Test
    public void acheterBestPractice() throws Exception {
        // 1er appeler url pour créer un article et récupérer l'article
        ArticleDto article = new ArticleDto(null, "article de test", 10);
        MockHttpServletResponse response = mvc.perform(post("/rest/articles")
                .content(asJsonString(article))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        String responseAsString = response.getContentAsString();
        System.out.println(responseAsString);
        // TODO extraire l'id article

        AchatDto achatDto = new AchatDto();
        achatDto.setArticleId(21); // mettre le bon id article
        achatDto.setArticleId(1);
        List<AchatDto> achats = singletonList(achatDto);
        String json = asJsonString(achats);

        mvc.perform(post("/rest/acheter")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // TODO  appeler url pour lister les factures et controler que ma nouvelle facture est la.
    }

    final ObjectMapper mapper = new ObjectMapper();

    /**
     * Méthode utilitaire pour créer du json à partir d'un object
     */
    public String asJsonString(final Object obj) {
        try {
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
