//package MapEditor;
//
//
//import org.lwjgl.opengl.Display;
//import static helpers.Controller.*;
//
//public class Screen {
//
//	
//	public static final int WIDTH = 600, HEIGHT = 400;
//	
//	public Screen() {
//		
//		startController();
//		
//		while(!Display.isCloseRequested()) {
//
//			drawQuad(50, 50, 100, 100);
//			drawQuad(150, 150, 100, 100);
//			
//			Display.update();
//			Display.sync(30);
//		}
//		
//		Display.destroy();
//	}
//	
//	public static void main (String[] args) {
//		new Screen();
//	}
//}
