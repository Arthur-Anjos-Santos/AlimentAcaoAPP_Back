package com.example.AlimentaAcaoAPP.Infra.Gateway;

import com.example.AlimentaAcaoAPP.Entities.EnderecoViaCep;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsultaCep {

    private final WebClient webClient;

    public ConsultaCep() {
        this.webClient = WebClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public EnderecoViaCep buscarEnderecoPorCep(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(EnderecoViaCep.class)
                .block();
    }
}
