package coursemanager.model;

public class RegisterNode {
	Register data;
	RegisterNode next;
	
	public RegisterNode() {
		
	}
	
	public RegisterNode(Register data, RegisterNode next) {
		this.data = data;
		this.next = next;
	}
	
	public RegisterNode(Register data) {
		this.data = data;
		this.next = null;
	}

}
