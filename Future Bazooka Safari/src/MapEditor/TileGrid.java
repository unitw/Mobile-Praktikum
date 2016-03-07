//package MapEditor;
//
//import static MapEditor.Controller.*;
//
//public class TileGrid {
//
//    /**
//     *
//     */
//    public Tile[][] map;
//	
//	public TileGrid() {
//		map = new Tile[15][15];
//		for(int i=0;i<map.length;i++) {
//			for(int j=0;j<map.length;j++) {
//				map[i][j] = new Tile(i*64, j*64, 64, 64, NewEnum.gras);
//			}
//		}
//	}
//	
//	public TileGrid(int[][] newMap) {
//		map = new Tile[15][15];
//		for(int i=0;i<map.length;i++) {
//			for(int j=0;j<map.length;j++) {
//				if (newMap[j][i] == 0) {
//					map[i][j] = new Tile( i*64, j*64, 64, 64, NewEnum.gras);
//				} else {
//					map[i][j] = new Tile( i*64, j*64, 64, 64, NewEnum.erde);
//				}
//			}
//		}
//		
//	}
//	
//	public void Draw() {
//            for (Tile[] map1 : map) {
//                for (int j = 0; j<map.length; j++) {
//                    Tile tile = map1[j];
//                    DrawQuadTexture(tile.getPosx(), tile.getPosy(), tile.getWidth(), tile.getHeight(), tile.getTexture());
//                }
//            }
//	}
//}