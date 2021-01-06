package com.example.demo.controller.clientsidetemplating;

import com.example.demo.dto.AchatDto;
import com.example.demo.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller pour exposition d'api REST des articles, utilisé dans le cas d'une application client side templating.
 */
@RestController
@RequestMapping("rest/acheter")
public class AcheterRestController {

    @Autowired
    private FactureService factureService;

    /**
     * Exposition d'une api déclenchée sur l'url http://..../articles.
     *
     * @return la liste des articles au format JSON. Voir la classe ArticleDto pour le détail du format.
     */
    @PostMapping
    public void acheter(@RequestBody List<AchatDto> achats) {
        System.out.println(achats);
        factureService.creerFacture(achats);
    }

}
