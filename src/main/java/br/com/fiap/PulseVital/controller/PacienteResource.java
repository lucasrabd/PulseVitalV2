package br.com.fiap.PulseVital.controller;

import br.com.fiap.PulseVital.model.entity.Paciente;
import br.com.fiap.PulseVital.model.repository.PacienteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/paciente")
public class PacienteResource {

    private final PacienteRepository pacienteRepository = new PacienteRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(Paciente paciente) {
        try {
            Paciente createdPaciente = pacienteRepository.save(paciente);
            if (createdPaciente != null) {
                return Response.ok(createdPaciente).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erro ao cadastrar paciente")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar a requisição: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPacientes() {
        try {
            return Response.ok(pacienteRepository.getAllPacientes()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar todos os pacientes: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacienteById(@PathParam("id") Long id) {
        try {
            Paciente paciente = pacienteRepository.getPacienteById(id);
            if (paciente != null) {
                return Response.ok(paciente).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente não encontrado")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar paciente por ID: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/cpf/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacienteByCpf(@PathParam("cpf") String cpf) {
        try {
            Paciente paciente = pacienteRepository.getPacienteByCpf(cpf);
            if (paciente != null) {
                return Response.ok(paciente).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente não encontrado")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar paciente por CPF: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPaciente(@PathParam("id") Long id, Paciente paciente) {
        try {
            paciente.setId(id);
            boolean updated = pacienteRepository.updatePaciente(paciente);

            if (updated) {
                return Response.ok("Paciente atualizado com sucesso").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente não encontrado para atualização")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar paciente: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deletePaciente(@PathParam("id") Long id) {
        try {
            boolean deleted = pacienteRepository.deletePaciente(id);
            if (deleted) {
                return Response.ok("Paciente excluído com sucesso").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente não encontrado para exclusão")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir paciente: " + e.getMessage())
                    .build();
        }
    }
}
