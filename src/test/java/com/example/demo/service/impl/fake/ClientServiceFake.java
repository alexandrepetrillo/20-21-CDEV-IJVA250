package com.example.demo.service.impl.fake;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

import java.util.Collections;
import java.util.List;

public class ClientServiceFake implements ClientService {
    @Override
    public List<Client> findAllClients() {
        Client clientFake = new Client();
        return Collections.singletonList(clientFake);
    }
}
