package rpg.graphics;

/**
 * This class is meant to represent data in an Entity. It has no use currently, ignore it.
 *
 */
public class Data {

	private final String dataName;
	private Object value;
	
	public Data(String dataName, Object value) {
		this.dataName = dataName;
		this.value = value;
	}
	
	public String getName() {
		return this.dataName;
		
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
}
