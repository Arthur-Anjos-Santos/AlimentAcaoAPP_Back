package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.DTOs.PessoaDTO;
import com.example.AlimentaAcaoAPP.Entities.Endereco;
import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import com.example.AlimentaAcaoAPP.Repository.EnderecoRepository;
import com.example.AlimentaAcaoAPP.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsuarioService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<PessoaDTO> listaUsuarios() {
        return repository.findAll()
                         .stream()
                         .map(PessoaDTO::new)
                         .collect(Collectors.toList());
    }

    public void criaUsuario(PessoaDTO pessoaDTO) {

        pessoaDTO.getUsuario().setSenha(passwordEncoder.encode(pessoaDTO.getUsuario().getSenha()));

        Endereco endereco = new Endereco(pessoaDTO.getEndereco());
        Pessoa pessoa = new Pessoa(pessoaDTO.getUsuario(), pessoaDTO);
        pessoa.setEndereco(endereco);

        enderecoRepository.save(endereco);
        repository.save(pessoa);
    }

    public void atualizarRendaERedefinirBeneficio(Integer idUsuario, BigDecimal rendaPerCapita, boolean ehBeneficiario, int quantidadePessoas, BigDecimal rendaTotal) {
        Optional<Pessoa> optionalPessoa = repository.findById(idUsuario);
        if (optionalPessoa.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Pessoa pessoa = optionalPessoa.get();
        pessoa.setQuantidadePessoas(quantidadePessoas);
        pessoa.setValorRendaPercapita(rendaPerCapita);
        pessoa.setEhBeneficiario(ehBeneficiario);
        pessoa.setRendaTotal(rendaTotal);

        repository.save(pessoa);
    }

    public Integer findByIdUsuario(String email) {
        Optional<Pessoa> usuario =  repository.findByEmail(email);
        return usuario.get().getUsuario().getId();
    }
}
