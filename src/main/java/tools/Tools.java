package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tools {

	public static int[][] loadFile (String fileName, int numlines){
		int[][] data = new int[numlines][];
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			f = new File(fileName);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			for (int i=0; i<numlines; i++) {
				String sData[] = br.readLine().split(" ");
				int iData[] = new int[sData.length];
				for (int j=0; j<iData.length; j++) {
					iData[j] = Integer.parseInt(sData[j]);
				}
				data[i] = iData;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return data;
	}

	public static void writeToFile (String fileName, int[][] data) {
		File f = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			f = new File(fileName);
			if (f.exists()) {
				f.delete();
			}
			f.createNewFile();
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for (int i=0; i<data.length; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j=0; j<data[i].length; j++) {
					if (j>0) sb.append(" ");
					sb.append(data[i][j]);
				}
				bw.write(sb.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
