package br.com.fiap.PulseVital.controller;


	import br.com.fiap.PulseVital.model.entity.Medico;
import br.com.fiap.PulseVital.model.services.MedicoServices;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



	@Path("/medico")
	public class MedicoResource {

		@GET
		@Path("/id/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findById(@PathParam("id") Long id) {
		    return new MedicoServices().findById(id);
		}


		@GET
		@Path("/nome/{nome}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findByNome(@PathParam("nome") String nome) {
		    return new MedicoServices().findByNome(nome);
		}

		
		@GET
		@Path("/crm/{crm}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findByCrm(@PathParam("crm") String crm) {
		    return new MedicoServices().findByCrm(crm);
		}

		
		@GET
		@Path("/findall")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findAll() {
		    return new MedicoServices().findAll();
		}
		
		@POST
		@Path("/save")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response save(@Valid Medico medico) {
	        return new MedicoServices().save(medico);
	    }

		
		 @DELETE
		 @Path("/delete/{id}")
		    public Response delete(@PathParam("id") Long id) {
		        return new MedicoServices().delete(id);
		    }
		
		 @PUT
		 @Path("/update")
		    @Consumes(MediaType.APPLICATION_JSON)
		    @Produces(MediaType.APPLICATION_JSON)
		    public Response update(@Valid Medico medico) {
		        return new MedicoServices().update(medico);
		    }
		}

