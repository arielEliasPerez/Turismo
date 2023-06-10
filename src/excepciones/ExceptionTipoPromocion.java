package excepciones;

public class ExceptionTipoPromocion extends Exception{
	private String msj;
	
	public ExceptionTipoPromocion(String msj){
		this.msj = msj;
	}
	
	public String toString() {
		return this.msj;
	}
}
