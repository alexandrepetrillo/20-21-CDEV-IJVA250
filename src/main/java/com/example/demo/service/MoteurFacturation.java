package com.example.demo.service;

import com.example.demo.dto.AchatDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;

import java.util.List;

public interface MoteurFacturation {
    Facture facturer(List<AchatDto> achats, Client client);
}
