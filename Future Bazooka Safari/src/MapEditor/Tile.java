package MapEditor;

import org.newdawn.slick.opengl.Texture;
import static MapEditor.Controller.*;

public class Tile {

	private double posx, posy, width, height;
	private Texture texture;
	private NewEnum typ;
	
	public Tile(double posx, double posy, double height, double width, NewEnum typ) {
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.texture = QuickLoad(typ.textureName);
		this.typ = typ;
	}
	
	public void Draw() {
		DrawQuadTexture(posx, posy, width, height, texture);
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public NewEnum getTyp() {
		return typ;
	}

	public void setTyp(NewEnum typ) {
		this.typ = typ;
	}
}
