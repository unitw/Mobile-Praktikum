package MapEditor;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Controller {

	private final static int width = 960, height = 640;
	
	public static void startController(String title) {
		Display.setTitle(title);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public static void DrawQuadTexture(double posx, double posy, double width, double height, Texture texture) {
		// An opengl binden.
		texture.bind();
		GL11.glTranslated(posx, posy, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0); 		// unten links
		GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(1, 0);		// unten rechts
		GL11.glVertex2d(width, 0);
		GL11.glTexCoord2d(1, 1);		// oben rechts
		GL11.glVertex2d(width, height);
		GL11.glTexCoord2d(0, 1);		// oben links
		GL11.glVertex2d(0, height);
		GL11.glEnd();
		GL11.glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tex;
	}
	
	public static Texture QuickLoad(String name) {
		Texture texture = null;
		texture = LoadTexture("resources/" + name + ".png", "PNG");
		return texture;
	}
}
