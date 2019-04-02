package jdbc;

public class Step {
	private String id;
	private int stepNumber;
	private String instructions;
	
	public Step(int stepNumber, String instructions) {
		this.stepNumber = stepNumber;
		this.instructions = instructions;
	}
	public String getId() {
		return id;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Override
	public String toString() {
		return "Steps [id=" + id + ", stepNumber=" + stepNumber + ", instructions=" + instructions + "]";
	}
	
}
