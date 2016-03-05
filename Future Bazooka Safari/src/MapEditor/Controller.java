//package MapEditor;
//
//import static org.lwjgl.opengl.GL11.GL_QUADS;
//import static org.lwjgl.opengl.GL11.glBegin;
//import static org.lwjgl.opengl.GL11.glEnd;
//import static org.lwjgl.opengl.GL11.glVertex2d;
//
//import org.lwjgl.LWJGLException;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;
//
//public class Controller {
//
//	public static final int WIDTH = 600, HEIGHT = 400;
//	
//	public static void startController() {
//		Display.setTitle("FBS_Game");
//		
//		try {
//			Display.setDisplayMode(new DisplayMode(600, 400));
//			Display.create();
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//		}
//		
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
//		GL11.glOrtho(0, 600, 400, 0, 1, -1);
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//	}
//	
//	public static void drawQuad(double posx, double posy, double width, double height) {
//		glBegin(GL_QUADS);
//		glVertex2d(posx, posy); 					//Links oben
//		glVertex2d(posx + width, posy); 			//Rechts oben
//		glVertex2d(posx + width, posy + height); 	//Unten rechts
//		glVertex2d(100, posy + height); 			//Unten links
//		glEnd();
//	}
//}
