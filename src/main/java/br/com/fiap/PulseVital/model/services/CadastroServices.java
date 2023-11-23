package br.com.fiap.PulseVital.model.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.PulseVital.model.entity.Cadastro;
import br.com.fiap.PulseVital.model.repository.CadastroRepository;
import jakarta.ws.rs.core.Response;

public class CadastroServices {

    private final ObjectMapper objectMapper;

    public CadastroServices() {
        this.objectMapper = new ObjectMapper();
    }

    public Response findAll() {
        List<Cadastro> cadastros = CadastroRepository.findAll();
        return buildListResponse(cadastros, "Lista de cadastros obtida com sucesso", "Nenhum cadastro encontrado");
    }

    private Response buildListResponse(List<?> data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (!data.isEmpty()) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("cadastros", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        return buildJsonResponse(responseMap);
    }

    private Response buildJsonResponse(Object entity) {
        return Response.ok(entity).build();
    }

    public Response findById(Long id) {
        Cadastro cadastroEncontrado = CadastroRepository.findById(id);
        return buildResponse(cadastroEncontrado, "Cadastro encontrado com sucesso", "Nenhum cadastro encontrado para o ID: " + id);
    }

    private Response buildResponse(Object data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (data != null) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("cadastro", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        try {
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
        }
    }

    public Response findByNome(String nome) {
        List<Cadastro> cadastrosEncontrados = CadastroRepository.findByNome(nome);

        Map<String, Object> responseMap = new HashMap<>();

        if (!cadastrosEncontrados.isEmpty()) {
            responseMap.put("mensagem", "Cadastro encontrado com sucesso");
            responseMap.put("cadastros", cadastrosEncontrados);
        } else {
            responseMap.put("mensagem", "Nenhum cadastro encontrado para o nome: " + nome);
        }

        try {
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
        }
    }

    public Response findByEmail(String email) {
        Cadastro cadastroEncontrado = CadastroRepository.findByEmail(email);

        Map<String, Object> responseMap = new HashMap<>();

        if (cadastroEncontrado != null) {
            responseMap.put("mensagem", "Cadastro encontrado com sucesso");
            responseMap.put("cadastro", cadastroEncontrado);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Nenhum cadastro encontrado para o email: " + email);

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response save(Cadastro cadastro) {
        Cadastro cadastroSalvo = CadastroRepository.save(cadastro);

        Map<String, Object> responseMap = new HashMap<>();

        if (cadastroSalvo != null) {
            responseMap.put("mensagem", "Cadastro salvo com sucesso");
            responseMap.put("cadastro", cadastroSalvo);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao salvar o cadastro");

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response delete(Long id) {
        if (CadastroRepository.delete(id)) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Cadastro excluído com sucesso");
            return Response.ok(responseMap).build();
        } else {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Erro ao excluir o cadastro");

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response update(Cadastro cadastro) {
        Cadastro cadastroAtualizado = CadastroRepository.update(cadastro);

        Map<String, Object> responseMap = new HashMap<>();

        if (cadastroAtualizado != null) {
            responseMap.put("mensagem", "Cadastro atualizado com sucesso");
            responseMap.put("cadastro", cadastroAtualizado);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao atualizar o cadastro");

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }
}
