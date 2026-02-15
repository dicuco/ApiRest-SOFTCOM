package servidor;

import java.util.Arrays;
import java.util.List;

import calculadora.OperacionesCalculadora;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Operaciones;
import modelo.Resultado;

/**
 * Software de Comunicaciones 25/26
 * <p>
 * Esta clase es el adaptador de OperacionesCalculadora.
 * Expone un API para su uso de acuerdo a las especificaciones de la práctica.
 * </p>
 */

//http://localhost:8080/api-docs

@Tag(
		name="calculadora",
		description="ApiRest calculadora"
)
@Path("/calculadora")
public class AdaptadorOperacionesCalculadoraREST {
    private static final OperacionesCalculadora ops = new OperacionesCalculadora();
    
    @Operation(
    	    summary = "Sumar dos operandos",
    	    description = "devuelve la suma de dos operandos",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Operación realizada correctamente",
    	            content = @Content(
    	                schema = @Schema(implementation = Resultado.class)
    	            )
    	        )
    	    }
    	)
	@GET
    @Path("/sumar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response sumar(@QueryParam("operando1") double op1, @QueryParam("operando2") double op2) {
        double resultado = ops.implementacionSumar(op1, op2);
        return Response.ok(new Resultado(resultado)).build();
    }
	
    @Operation(
    	    summary = "Restar dos operandos",
    	    description = "Devuelve la resta de dos operandos",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Operación realizada correctamente",
    	            content = @Content(
    	                schema = @Schema(implementation = Resultado.class)
    	            )
    	        )
    	    }
    	)
	@GET
    @Path("/restar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response restar(@QueryParam("operando1") double op1, @QueryParam("operando2") double op2) {
        double resultado = ops.implementacionRestar(op1, op2);
        return Response.ok(new Resultado(resultado)).build();
    }
	
    @Operation(
    	    summary = "Multiplicar dos operandos",
    	    description = "devuelve la multiplicacion de dos operandos",
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Operación realizada correctamente",
    	            content = @Content(
    	                schema = @Schema(implementation = Resultado.class)
    	            )
    	        )
    	    }
    	)
	@GET
    @Path("/multiplicar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response multiplicar(@QueryParam("operando1") double op1, @QueryParam("operando2") double op2) {
        double resultado = ops.implementacionMultiplicar(op1, op2);
        return Response.ok(new Resultado(resultado)).build();
    }
	
	@Operation(
		    summary = "Divide dos números",
		    description = "Devuelve la division de dos numeros"
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200",
		        description = "Resultado de la división",
		        content = @Content(schema = @Schema(implementation = Resultado.class))
		    ),
		    @ApiResponse(
		        responseCode = "412",
		        description = "División no válida (divisor cero o indeterminación)"
		    )
		})
	@GET
	@Path("/dividir")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response dividir(@QueryParam("dividendo") double dividendo, @QueryParam("divisor") double divisor) {
	    
	    try {
	        double res = ops.implementacionDividir(dividendo, divisor);
	        //return Response.ok(res).build();
		    return Response.ok(new Resultado(res)).build();

	    } catch (Exception e) {
	        return Response.status(412)
	                .entity(e.getMessage())
	                .type(MediaType.TEXT_PLAIN)
	                .build();
	    }
	}
	
	@Operation(
		    summary = "Obtener último resultado",
		    description = "Devuelve el último resultado",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Último resultado obtenido",
		            content = @Content(
		                schema = @Schema(implementation = Resultado.class)
		            )
		        )
		    }
		)
	@GET
	@Path("/ur")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getUR() {
	    double valor = ops.implementacionObtenerUR();
	    return Response.ok(new Resultado(valor)).build();
	}
	
	@Operation(
		    summary = "Obtener memoria",
		    description = "Devuelve el valor de la memoria",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Valor de la memoria",
		            content = @Content(
		                schema = @Schema(implementation = Resultado.class)
		            )
		        )
		    }
		)
	@GET
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMemoria() {
	    double valor = ops.implementacionMO();
	    return Response.ok(new Resultado(valor)).build();
	}
	
	
	@Operation(
		    summary = "Añadir a memoria",
		    description = "Añade el último resultado a la memoria",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Último resultado añadido a memoria"
		        )
		    }
		)
	@HEAD
	@Path("/memoria")
	public Response addMemoria() {
	    ops.implementacionMA();
	    return Response.ok().build(); 
	}

	@Operation(
		    summary = "Limpiar memoria",
		    description = "Borra el contenido de la memoria",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Memoria borrada correctamente"
		        )
		    }
		)
	@DELETE
	@Path("/memoria")
	public Response clearMemoria() {
	    ops.implementacionML();
	    return Response.ok().build(); 
	}
	
	@Operation(
		    summary = "Obtener lista de operaciones ",
		    description = "Devuelve una lista con los nombres de las operaciones disponibles",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Lista de operaciones",
		            content = @Content(
		                schema = @Schema(implementation = Operaciones.class)
		            )
		        )
		    }
		)
	@GET
	@Path("/operaciones")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Operaciones getOperaciones(
	        @QueryParam("numeroOperaciones") int numero) {

	    String[] todas = { "x^2", "sqrt(x)", "ln(x)", "tg(x)" };
	    List<String> lista = Arrays.asList(todas).subList(0, numero);

	    return new Operaciones(lista);
	}
	
	@Operation(
		    summary = "Ejecutar una operación unaria",
		    description = "Ejecuta una operación ",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Resultado de la operación",
		            content = @Content(
		                schema = @Schema(implementation = Resultado.class)
		            )
		        ),
		        @ApiResponse(
		            responseCode = "412",
		            description = "Operación no válida o error matemático",
		            content = @Content(mediaType = "text/plain")
		        )
		    }
		)
	@Path("/operar")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response operar(
	        @QueryParam("operacion") int operacion,
	        @QueryParam("operando") double operando) {

	    try {
	        double resultado=0;

	        switch (operacion) {
	            case 0:
	                resultado = ops.implementacionCuadrado(operando);
	                break;
	            case 1:
	                resultado = ops.implementacionRaizCuadrada(operando);
	                break;
	            case 2:
	                resultado = ops.implementacionLg(operando);
	                break;
	            case 3:
	                resultado = ops.implementacionTg(operando);
	                break;
	        }

	        return Response.ok(new Resultado(resultado)).build();

	    } catch (Exception e) {
	        return Response.status(412)
	                .entity(e.getMessage())
	                .type(MediaType.TEXT_PLAIN)
	                .build();
	    }
	    
	}
	
}

