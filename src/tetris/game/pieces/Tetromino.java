package tetris.game.pieces;

import java.util.ArrayList;

public class Tetromino {

	public static final ArrayList<Tetromino> LIST = new ArrayList<>();
	
	public static final Tetromino I;
	public static final Tetromino J;
	public static final Tetromino L;
	public static final Tetromino O;
	public static final Tetromino S;
	public static final Tetromino Z;
	public static final Tetromino T;
	
	static {
		I = new Tetromino("light_blue", 4);
		I.setBlocks(Rotation.ROT0, 0,1, 1,1, 2,1, 3,1);
		I.setBlocks(Rotation.ROT90, 1,0, 1,1, 1,2, 1,3);
		I.setBlocks(Rotation.ROT180, 0,2, 1,2, 2,2, 3,2);
		I.setBlocks(Rotation.ROT270, 2,0, 2,1, 2,2, 2,3);
		J = new Tetromino("orange", 3);
		J.setBlocks(Rotation.ROT0, 0,0, 0,1, 1,1, 2,1);
		J.setBlocks(Rotation.ROT90, 1,0, 2,0, 1,1, 1,2);
		J.setBlocks(Rotation.ROT180, 0,1, 1,1, 2,1, 2,2);
		J.setBlocks(Rotation.ROT270, 1,0, 1,1, 1,2, 0,2);
		L = new Tetromino("blue", 3);
		L.setBlocks(Rotation.ROT0, 0,1, 1,1, 2,1, 2,0);
		L.setBlocks(Rotation.ROT90, 1,0, 1,1, 1,2, 2,2);
		L.setBlocks(Rotation.ROT180, 0,1, 1,1, 2,1, 0,2);
		L.setBlocks(Rotation.ROT270, 0,0, 1,0, 1,1, 1,2);
		O = new Tetromino("yellow", 2);
		O.setBlocks(Rotation.ROT0, 0,0, 1,0, 0,1, 1,1);
		O.setBlocks(Rotation.ROT90, 0,0, 1,0, 0,1, 1,1);
		O.setBlocks(Rotation.ROT180, 0,0, 1,0, 0,1, 1,1);
		O.setBlocks(Rotation.ROT270, 0,0, 1,0, 0,1, 1,1);
		S = new Tetromino("red", 3);
		S.setBlocks(Rotation.ROT0, 0,1, 1,1, 1,0, 2,0);
		S.setBlocks(Rotation.ROT90, 1,0, 1,1, 2,1, 2,2);
		S.setBlocks(Rotation.ROT180, 0,2, 1,2, 1,1, 2,1);
		S.setBlocks(Rotation.ROT270, 0,0, 0,1, 1,1, 1,2);
		Z = new Tetromino("green", 3);
		Z.setBlocks(Rotation.ROT0, 0,0, 1,0, 1,1, 2,1);
		Z.setBlocks(Rotation.ROT90, 2,0, 2,1, 1,1, 1,2);
		Z.setBlocks(Rotation.ROT180, 0,1, 1,1, 1,2, 2,2);
		Z.setBlocks(Rotation.ROT270, 1,0, 1,1, 0,1, 0,2);
		T = new Tetromino("purple", 3);
		T.setBlocks(Rotation.ROT0, 1,0, 0,1, 1,1, 1,2);
		T.setBlocks(Rotation.ROT90, 0,1, 1,1, 2,1, 1,2);
		T.setBlocks(Rotation.ROT180, 1,0, 1,1, 1,2, 2,1);
		T.setBlocks(Rotation.ROT270, 0,1, 1,0, 1,1, 2,1);
	}
	
	private int[][] blocksPositions;
	
	private String color;
	private int size;
	
	/**Initializes tetromino*/
	public Tetromino(String color, int size) {
		this.blocksPositions = new int[4][size*2];
		this.color = color;
		this.size = size;
		LIST.add(this);
	}
	
	/**Gets the square at given coords<br>
	 * Since in the array are only stored ints this will create a new square if the given x and y coordinates exists in the array
	 * @param currentRotation - The rotation of the tetromino that is falling in Playing state
	 * @param x - Coordinate x
	 * @param y - Coordinate y
	 * @return A newly created square if the given coordinates are correct, null if the given coordinates do not point to a square
	 */
	public Square getSquareAt(Rotation currentRotation, int x, int y) {
		if((this.blocksPositions[currentRotation.posInArray][0] == x && this.blocksPositions[currentRotation.posInArray][1] == y) ||
		   (this.blocksPositions[currentRotation.posInArray][2] == x && this.blocksPositions[currentRotation.posInArray][3] == y) ||
		   (this.blocksPositions[currentRotation.posInArray][4] == x && this.blocksPositions[currentRotation.posInArray][5] == y) ||
		   (this.blocksPositions[currentRotation.posInArray][6] == x && this.blocksPositions[currentRotation.posInArray][7] == y)) {
			return new Square(color);
		}
		else {
			return null;
		}
	}
	
	/**Sets the squares for this tetrominoes
	 * @param rot - Determines wich of the 4 rotations
	 * @param coords - An array of length 8 (4*2) that represents the x and y coords of the squares
	 */
	private void setBlocks(Rotation rot, int... coords) {
		this.blocksPositions[rot.posInArray] = coords;
	}
	
	public int getSize() {
		return size;
	}
	
	public enum Rotation {
		ROT0(0),
		ROT90(1),
		ROT180(2),
		ROT270(3);
		
		private int posInArray;
		
		Rotation(int posInArray) {
			this.posInArray = posInArray;
		}

		public Rotation rotateClockwise() {
			if(posInArray > 0)
				return Rotation.values()[posInArray-1];
			else
				return Rotation.values()[3];
		}
		
		public Rotation rotateCounterclockwise() {
			if(posInArray < 3)
				return Rotation.values()[posInArray+1];
			else
				return Rotation.values()[0];
		}
	}
}
