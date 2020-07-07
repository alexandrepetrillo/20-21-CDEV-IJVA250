package com.example.demo.service;

import com.example.demo.controller.clientsidetemplating.dto.AchatDto;
import com.example.demo.entity.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> findAllFactures();

    Facture findById(Long id);

    Facture creerFacture(List<AchatDto> achats);
}
