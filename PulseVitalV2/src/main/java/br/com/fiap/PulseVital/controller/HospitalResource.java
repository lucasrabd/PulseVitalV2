package br.com.fiap.PulseVital.controller;


	import br.com.fiap.PulseVital.model.entity.Hospital;
import br.com.fiap.PulseVital.model.services.HospitalServices;
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



	@Path("/hospital")
	public class HospitalResource {

		@GET
		@Path("/id/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findById(@PathParam("id") Long id) {
		    return new HospitalServices().findById(id);
		}


		@GET
		@Path("/nome/{nome}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findByNome(@PathParam("nome") String nome) {
		    return new HospitalServices().findByNome(nome);
		}

		
		@GET
		@Path("/findall")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findAll() {
		    return new HospitalServices().findAll();
		}
		
		@POST
		@Path("/save")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response save(@Valid Hospital hospital) {
		    return new HospitalServices().save(hospital); 
		}

		
		 @DELETE
		 @Path("/delete/{id}")
		    public Response delete(@PathParam("id") Long id) {
		        return new HospitalServices().delete(id);
		    }
		
		 @PUT
		 @Path("/update")
		    @Consumes(MediaType.APPLICATION_JSON)
		    @Produces(MediaType.APPLICATION_JSON)
		    public Response update(@Valid Hospital hospital) {
		        return new HospitalServices().update(hospital);
		    }
		}

