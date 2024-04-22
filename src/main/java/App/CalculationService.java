package App;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Ejbs.Calculation;



@Stateless
@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CalculationService {

	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;

	
	@GET
	@Path("try")
	public String tryThis() {
	  
	  return "This is a simple message!";
	}

	
	@POST
    @Path("calc")
	public Response createCalculation(Calculation request) {
	    try {
	      int number1 = request.getNumber1();
	      int number2 = request.getNumber2();
	      String operation = request.getOperation();
	      int result = performOperation(number1, number2, operation);

	      Calculation calculation = new Calculation(number1, number2, operation, result);
	      entityManager.persist(calculation);

	      // Directly return the persisted Calculation object (excluding sensitive data if needed)
	      return Response.ok(calculation).build();
	    } catch (ArithmeticException e) {
	      return Response.status(Response.Status.BAD_REQUEST).entity("Division by zero is not allowed.").build();
	    } catch (IllegalArgumentException e) {
	      return Response.status(Response.Status.BAD_REQUEST).entity("Invalid operation type.").build();
	    } catch (Exception e) {
	      // Handle other unexpected exceptions with a generic message
	      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred during calculation.").build();
	    }
	  }
	
	@GET
    @Path("calculations")
	public Response getCalculations() {
	    try {
	      List<Calculation> calculations = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
	      return Response.ok(calculations).build();
	    } catch (Exception e) {
	      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while retrieving calculations.").build();
	    }
	  }

	  private int performOperation(int number1, int number2, String operation) {
	    switch (operation) {
	      case "+":
	        return number1 + number2;
	      case "-":
	        return number1 - number2;
	      case "*":
	        return number1 * number2;
	      case "/":
	        if (number2 != 0) {
	          return number1 / number2;
	        } else {
	          throw new IllegalArgumentException("Division by zero is not allowed.");
	        }
	      default:
	        throw new IllegalArgumentException("Invalid operation type.");
	    }
	  }

	

}
