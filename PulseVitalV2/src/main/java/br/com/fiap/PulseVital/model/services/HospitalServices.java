package br.com.fiap.PulseVital.model.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.PulseVital.model.entity.Hospital;
import br.com.fiap.PulseVital.model.repository.HospitalRepository;
import jakarta.ws.rs.core.Response;

public class HospitalServices {

    private final ObjectMapper objectMapper;

    public HospitalServices() {
        this.objectMapper = new ObjectMapper();
    }

    public Response findAll() {
        List<Hospital> hospitais = HospitalRepository.findAll();
        return buildListResponse(hospitais, "Lista de hospitais obtida com sucesso", "Nenhum hospital encontrado");
    }

    private Response buildListResponse(List<?> data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (!data.isEmpty()) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("hospitais", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        return buildJsonResponse(responseMap);
    }

    private Response buildJsonResponse(Object entity) {
        return Response.ok(entity).build();
    }

    public Response findById(Long id) {
        Hospital hospitalEncontrado = HospitalRepository.findById(id);
        return buildResponse(hospitalEncontrado, "Hospital encontrado com sucesso", "Nenhum hospital encontrado para o ID: " + id);
    }

    private Response buildResponse(Object data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (data != null) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("hospital", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        try {
            // Configuração para formatar o JSON
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (JsonProcessingException e) {
            // Tratar exceção de formatação do JSON
            e.printStackTrace();
            return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
        }
    }

    public Response findByNome(String nome) {
        List<Hospital> hospitaisEncontrados = HospitalRepository.findByNome(nome);

        Map<String, Object> responseMap = new HashMap<>();

        if (!hospitaisEncontrados.isEmpty()) {
            responseMap.put("mensagem", "Hospital encontrado com sucesso");
            responseMap.put("hospitais", hospitaisEncontrados);
        } else {
            responseMap.put("mensagem", "Nenhum hospital encontrado para o nome: " + nome);
        }

        try {
            // Configuração para formatar o JSON
            String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
            return Response.ok(jsonResponse).build();
        } catch (JsonProcessingException e) {
            // Tratar exceção de formatação do JSON
            e.printStackTrace();
            return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
        }
    }

    public Response save(Hospital hospital) {
        Hospital hospitalSalvo = HospitalRepository.save(hospital);

        Map<String, Object> responseMap = new HashMap<>();

        if (hospitalSalvo != null) {
            responseMap.put("mensagem", "Hospital salvo com sucesso");
            responseMap.put("hospital", hospitalSalvo);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao salvar o hospital");

            try {
                // Configuração para formatar o JSON
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                // Tratar exceção de formatação do JSON
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response delete(Long id) {
        if (HospitalRepository.delete(id)) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Hospital excluído com sucesso");
            return Response.ok(responseMap).build();
        } else {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Erro ao excluir o hospital");

            try {
                // Configuração para formatar o JSON
                String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                // Tratar exceção de formatação do JSON
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }

    public Response update(Hospital hospital) {
        Hospital hospitalAtualizado = HospitalRepository.update(hospital);

        Map<String, Object> responseMap = new HashMap<>();

        if (hospitalAtualizado != null) {
            responseMap.put("mensagem", "Hospital atualizado com sucesso");
            responseMap.put("hospital", hospitalAtualizado);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao atualizar o hospital");

            try {
                // Configuração para formatar o JSON
                String jsonResponse = objectMapper
                    .writerWithDefaultPrettyPrinter().writeValueAsString(responseMap);
                return Response.ok(jsonResponse).build();
            } catch (JsonProcessingException e) {
                // Tratar exceção de formatação do JSON
                e.printStackTrace();
                return Response.status(500).entity("Erro ao formatar a resposta JSON").build();
            }
        }
    }
}
