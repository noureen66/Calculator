package Ejbs;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Stateless
@Entity
public class Calculation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    public int number1;
    public int number2;
    public String operation;
    public int result;
    public Calculation() {}
    public Calculation(int number1, int number2, String operation, int result) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
        this.result = result;
    }
    
    public Long getId() {
        return id;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public String getOperation() {
        return operation;
    }

    public int getResult() {
        return result;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
   
	
}

