package MapEditor;

public enum NewEnum {

	gras("gras", true), erde("erde", false);
	
	String textureName;
	boolean buildable;
	
	NewEnum(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
	
}
