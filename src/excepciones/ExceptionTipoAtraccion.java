package excepciones;

public class ExceptionTipoAtraccion extends Exception{
	private String msj;
	
	public ExceptionTipoAtraccion(String msj){
		this.msj = msj;
	}
	
	public String toString() {
		return this.msj;
	}
}
