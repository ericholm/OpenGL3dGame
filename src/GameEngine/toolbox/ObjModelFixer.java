package GameEngine.toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ObjModelFixer {

	private static final String RES_LOC = "res/modelFix/";
	private static final String G_RES_LOC = "res/modelFix/Group/";
	private static BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<String> vertices = new ArrayList<>();
	private static ArrayList<String> normals = new ArrayList<>();
	private static ArrayList<String> textures = new ArrayList<>();
	private static ArrayList<String> faces = new ArrayList<>();

	public static void main(String[] args) {
		
		try {
			System.out.print("Mode: ");
			String mode = bufferRead.readLine();
			System.out.println(mode.length());
			int fileNumber = 1;
			if (mode.charAt(0) == 'a') {
				System.out.print("File Number: ");
				fileNumber = Integer.valueOf(bufferRead.readLine());
			}
			System.out.print("File Name: ");
			String fileName;
			fileName = bufferRead.readLine();
			double startTime = System.currentTimeMillis();
			String loc = "";
			for (int i = 0; i < fileNumber; i++) {
				vertices.clear();
				normals.clear();
				textures.clear();
				faces.clear();
				String name;
				int count = 0;
				if (mode.charAt(0) == 'a') {
					loc = G_RES_LOC;
					if (i < 9) {
						name = fileName + "_00000" + (i + 1);
					}
					else {
						name = fileName + "_0000" + (i + 1);
					}
					
				}
				else {
					loc = RES_LOC;
					name = fileName;
				}
				File objFile = new File(loc + name + ".obj");
				BufferedReader fileReader = new BufferedReader( new FileReader(objFile));
				String line = "";
				System.out.println("Reading File: " + (i + 1));
				
				while (true) {
					line = fileReader.readLine();
					if (line == null) {
						break;
					}
					if (line.startsWith("v ")) {
						vertices.add(line);
					}
					else if (line.startsWith("vt ")) {
						textures.add(line);
					}
					else if (line.startsWith("vn ")) {
						normals.add(line);
					}
					else if (line.startsWith("f ")) {
						faces.add(line);
					}
					count++;
				}
				System.out.println("Finished Reading File: " + (i + 1) + ". Lines: " + count);
				System.out.println("Writing File: " + (i + 1));
				PrintWriter writer = new PrintWriter(loc + name + ".obj");
				for (String v : vertices) {
					writer.println(v);
				}
				for (String n : normals) {
					writer.println(n);
				}
				for (String t : textures) {
					writer.println(t);
				}
				for (String f : faces) {
					writer.println(f);
				}
				writer.close();
			}
			double finishTime = System.currentTimeMillis();
			double timeSeconds = finishTime - startTime / 1000;
			//System.out.println("Finished in: " + timeSeconds + " seconds");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
