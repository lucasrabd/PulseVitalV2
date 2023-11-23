package br.com.fiap.PulseVital.model.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.PulseVital.model.entity.Medico;
import br.com.fiap.PulseVital.model.repository.MedicoRepository;
import jakarta.ws.rs.core.Response;

public class MedicoServices {

    private final ObjectMapper objectMapper;

    public MedicoServices() {
        this.objectMapper = new ObjectMapper();
    }

    public Response findAll() {
        List<Medico> medicos = MedicoRepository.findAll();
        return buildListResponse(medicos, "Lista de médicos obtida com sucesso", "Nenhum médico encontrado");
    }

    private Response buildListResponse(List<?> data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (!data.isEmpty()) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("medicos", data);
        } else {
            responseMap.put("mensagem", nenhumEncontradoMsg);
        }

        return buildJsonResponse(responseMap);
    }

    private Response buildJsonResponse(Object entity) {
        return Response.ok(entity).build();
    }

    public Response findById(Long id) {
        Medico medicoEncontrado = MedicoRepository.findById(id);
        return buildResponse(medicoEncontrado, "Médico encontrado com sucesso", "Nenhum médico encontrado para o ID: " + id);
    }

    private Response buildResponse(Object data, String sucessoMsg, String nenhumEncontradoMsg) {
        Map<String, Object> responseMap = new HashMap<>();

        if (data != null) {
            responseMap.put("mensagem", sucessoMsg);
            responseMap.put("medico", data);
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
        List<Medico> medicosEncontrados = MedicoRepository.findByNome(nome);

        Map<String, Object> responseMap = new HashMap<>();

        if (!medicosEncontrados.isEmpty()) {
            responseMap.put("mensagem", "Médico encontrado com sucesso");
            responseMap.put("medicos", medicosEncontrados);
        } else {
            responseMap.put("mensagem", "Nenhum médico encontrado para o nome: " + nome);
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
    
    public Response findByCrm(String crm) {
        List<Medico> medicosEncontrados = MedicoRepository.findByCrm(crm);

        Map<String, Object> responseMap = new HashMap<>();

        if (!medicosEncontrados.isEmpty()) {
            responseMap.put("mensagem", "Médico encontrado com sucesso");
            responseMap.put("medicos", medicosEncontrados);
        } else {
            responseMap.put("mensagem", "Nenhum médico encontrado para o crm: " + crm);
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

    public Response save(Medico medico) {
        Medico medicoSalvo = MedicoRepository.save(medico);

        Map<String, Object> responseMap = new HashMap<>();

        if (medicoSalvo != null) {
            responseMap.put("mensagem", "Médico salvo com sucesso");
            responseMap.put("medico", medicoSalvo);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao salvar o médico");

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
        if (MedicoRepository.delete(id)) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Médico excluído com sucesso");
            return Response.ok(responseMap).build();
        } else {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("mensagem", "Erro ao excluir o médico");

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

    public Response update(Medico medico) {
    	Medico medicoAtualizado = MedicoRepository.update(medico);

        Map<String, Object> responseMap = new HashMap<>();

        if (medicoAtualizado != null) {
            responseMap.put("mensagem", "Medico atualizado com sucesso");
            responseMap.put("medico", medicoAtualizado);
            return Response.ok(responseMap).build();
        } else {
            responseMap.put("mensagem", "Erro ao atualizar o medico");

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

}
