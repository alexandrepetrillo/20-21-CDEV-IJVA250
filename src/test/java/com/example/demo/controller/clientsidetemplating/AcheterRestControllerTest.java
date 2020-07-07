package com.example.demo.controller.clientsidetemplating;

import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test unitaire de AcheterRestController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AcheterRestController.class)
public class AcheterRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FactureService factureService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void acheter() throws Exception {
        AchatDto achatDto = new AchatDto();
        achatDto.setArticleId(999);
        achatDto.setQuantite(1);
        List<AchatDto> achats = singletonList(achatDto);
        mvc.perform(post("/rest/acheter")
                .content(asJsonString(achats))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(factureService).creerFacture(anyList());
    }

    /**
     * Méthode utilitaire pour créer du json à partir d'un object
     */
    public String asJsonString(final Object obj) {
        try {
            String jsonContent = objectMapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
