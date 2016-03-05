package mapEditor;

public enum TileTyp {

	Gras("gras", true), Dreck("dreck", false);
	
	String textureName;
	boolean buildable;
	
	TileTyp(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
	
}
