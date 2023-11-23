package br.com.fiap.PulseVital.model.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.PulseVital.model.entity.Login;
import br.com.fiap.PulseVital.model.repository.LoginRepository;
import jakarta.ws.rs.core.Response;

public class LoginServices {

    private final ObjectMapper objectMapper;

    public LoginServices() {
        this.objectMapper = new ObjectMapper();
    }

    public Response findAll() {
        List<Login> logins = LoginRepository.findAll();
        return buildListResponse(logins, "Lista de logins obtida com sucesso", "Nenhum login encontrado");
    }

    public Response findById(Long id) {
        Login loginEncontrado = LoginRepository.findById(id);
        return buildResponse(loginEncontrado, "Login encontrado com sucesso", "Nenhum login encontrado para o ID: " + id);
    }

    public Response findByEmail(String email) {
        List<Login> loginsEncontrados = LoginRepository.findByEmail(email);
        return buildListResponse(loginsEncontrados, "Login encontrado com sucesso", "Nenhum login encontrado para o email: " + email);
    }

    public Response save(Login login) {
        Login loginSalvo = LoginRepository.save(login);

        Map<String, Object> responseMap = new HashMap<>();

        if (loginSalvo != null) {
            responseMap.put("mensagem", "Login salvo com sucesso");
            responseMap.put("login", loginSalvo);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao salvar o login");

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
        if (LoginRepository.delete(id)) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Login excluído com sucesso");
            return Response.ok(responseMap).build();
        } else {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Erro ao excluir o login");

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response update(Login login) {
        Login loginAtualizado = LoginRepository.update(login);

        Map<String, Object> responseMap = new HashMap<>();

        if (loginAtualizado != null) {
            responseMap.put("mensagem", "Login atualizado com sucesso");
            responseMap.put("login", loginAtualizado);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao atualizar o login");

            try {
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    private Response buildListResponse(List<?> data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (!data.isEmpty()) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("logins", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        return buildJsonResponse(responseMap);
    }

    private Response buildJsonResponse(Object entity) {
        return Response.ok(entity).build();
    }

    private Response buildResponse(Object data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (data != null) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("login", data);
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
}
