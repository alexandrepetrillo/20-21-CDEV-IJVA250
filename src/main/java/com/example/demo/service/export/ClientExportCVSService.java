package com.example.demo.service.export;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClientExportCVSService {

    @Autowired
    private ClientRepository clientRepository;

    public void export(PrintWriter writer) {
        writer.println("Nom;Prénom;Age");
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            int age = LocalDate.now().getYear() - client.getDateNaissance().getYear();
            String line = client.getNom() + ";" + client.getPrenom() + ";" + age;
            writer.println(line);
        }

    }


}
