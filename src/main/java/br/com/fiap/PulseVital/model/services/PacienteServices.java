package br.com.fiap.PulseVital.model.services;

import br.com.fiap.PulseVital.model.entity.Paciente;
import br.com.fiap.PulseVital.model.repository.PacienteRepository;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class PacienteServices {

    public Response getAllPacientes() {
        List<Paciente> pacientes = new PacienteRepository().getAllPacientes();
        return buildResponse(pacientes, "Lista de pacientes obtida com sucesso", "Nenhum paciente encontrado");
    }

    public Response getPacienteById(Long id) {
        Paciente paciente = new PacienteRepository().getPacienteById(id);
        return buildResponse(paciente, "Paciente encontrado com sucesso", "Nenhum paciente encontrado para o ID: " + id);
    }

    public Response getPacienteByCpf(String cpf) {
        Paciente paciente = new PacienteRepository().getPacienteByCpf(cpf);
        return buildResponse(paciente, "Paciente encontrado com sucesso", "Nenhum paciente encontrado para o CPF: " + cpf);
    }

    public Response deletePaciente(Long id) {
        boolean deleted = new PacienteRepository().deletePaciente(id);
        if (deleted) {
            return Response.ok("Paciente excluído com sucesso").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Paciente não encontrado").build();
        }
    }

    private Response buildResponse(Object data, String successMessage, String notFoundMessage) {
        if (data != null) {
            return Response.ok(data).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundMessage).build();
        }
    }
}

