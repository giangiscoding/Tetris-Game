package tetris.framework.resources;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ResourceManager {

	private static final HashMap<String, BufferedImage> TEXTURES = new HashMap<>();
	
	/**Reads all .png files inside res/textures folder<br>
	 * Call before game starts
	 */
	public static void readImageFiles() {
		File texturesFolder = new File("res/textures");
		for(File imgFile : texturesFolder.listFiles()) {
			TEXTURES.put(imgFile.getName(), getImageFromFile(imgFile));
		}
		System.out.println("[Framework][Resources]: Finished reading image files");
	}
	
	/**Gets a texture by its name*/
	public static BufferedImage texture(String name) {
		return TEXTURES.get(name);
	}
	
	private static BufferedImage getImageFromFile(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("[Framework][Resources]: Exception loading "+file.getName());
			System.exit(-1);
			return null;
		}
	}
	
	public static int[] readHighscoresFile() {
		int[] array = new int[9];
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader("res/highscores.txt"));
		} catch (FileNotFoundException e1) {
			System.out.println("[Framework][Resources]: File highscores.txt not found");
			System.exit(-1);
			return null;
		}
		
		try {
			String str = reader.readLine();
			while(str!=null) {
				String[] strarray = str.split("-");
				array[Integer.parseInt(strarray[0])] = Integer.parseInt(strarray[1]);
				
				str = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("[Framework][Resources]: IOException occurred when reading highscores");
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("[Framework][Resources]: IOException occurred when reading highscores");
			System.exit(-1);
			return null;
		}
		
		return array;
	}
	
	public static void writeHighscores(int[] scores) {
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(new File("res/highscores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("[ResourceManager]: File highscores.txt not found");
		}
		
		for(int i=0; i<scores.length; i++) {
			writer.println(i+"-"+scores[i]);
		}
		writer.close();
	}
}
