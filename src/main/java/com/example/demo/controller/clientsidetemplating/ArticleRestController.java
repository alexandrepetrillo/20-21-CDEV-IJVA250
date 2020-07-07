package com.example.demo.controller.clientsidetemplating;

import com.example.demo.controller.clientsidetemplating.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller pour exposition d'api REST des articles, utilisé dans le cas d'une application client side templating.
 */
@RestController
@RequestMapping("rest/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ArticleDto post(@RequestBody ArticleDto dto) {
        return articleService.create(dto);
    }

    @PostMapping("{id}/stock")
    public ArticleDto modifierStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return articleService.modifierStock(id, quantity);
    }

    /**
     * Exposition d'une api déclenchée sur l'url http://..../articles.
     *
     * @return la liste des articles au format JSON. Voir la classe ArticleDto pour le détail du format.
     */
    @GetMapping
    public List<ArticleDto> getArticles() {
        // Transformation d'une liste de Article en ArticleDto.
        return articleService.findAll();
    }

}
