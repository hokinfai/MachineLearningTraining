package MachineLearningTraining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Formatter {
	final StringBuilder output = new StringBuilder();
	String outPath;

	public Formatter(String outPath) {
		this.outPath = outPath;

	}

	public void formatter(String input) throws IOException {
		File file = new File(outPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		// true = append file
		FileWriter fileWritter = new FileWriter(file, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		// System.out.println("input: " + input);
		String[] spilter = input.split("\t");
		int beginIn = Integer.parseInt(spilter[0]);
		int endIn = Integer.parseInt(spilter[1]);
		String term = spilter[2];
		String POS = spilter[4];
		String chunk = spilter[5];
		String POSturn = POS;

		if (!term.matches("(.*\\..*)?(.*(-.+)+)?") || (term.equals(".") || term.equals("-"))) {
			output.append(input+"\n");
			bufferWritter.write(output.toString());
			System.out.println("Done");
			bufferWritter.close();
			System.out.println("output: " + output);
		} else {
			int point;

			if (term.matches("[0-9]+.*"))
				POSturn = "CD";
			// System.out.println(term);
			if (term.contains(".") && term.contains("-")) {
				int pointDoc = term.indexOf(".");
				int pointHy = term.indexOf("-");
				if ((pointDoc - pointHy) > 0)
					point = pointHy;
				else
					point = pointDoc;
			} else if (term.contains(".")) {
				point = term.indexOf(".");

			} else {
				point = term.indexOf("-");

			}
			if (term.startsWith(".")) {
				point = 1;
				POSturn = ".";
			} else if (term.startsWith("-")) {
				point = 1;
				POSturn = "HYPN";
			}

			output.append(beginIn + "\t" + (beginIn + point) + "\t" + term.substring(0, point) + "\t"
					+ term.substring(0, point) + "\t" + POSturn + "\t" + chunk + "\n");
			// System.out.println("insider: " + output);
			chunk = "I" + chunk.substring(1);
			String recur = (beginIn + point) + "\t" + endIn + "\t" + term.substring(point) + "\t"
					+ term.substring(point) + "\t" + POS + "\t" + chunk;
			// System.out.println("recur: " + recur);
			formatter(recur);
		}
		
	}

	public static void main(String args[]) throws IOException {

		String txtPath = "/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5.txt";
		String outPath = "/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5(new).txt";
		List<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(txtPath));
		try

		{
			String line = br.readLine();

			while (line != null) {

				// System.out.println("new term: " + line);
				if (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))
					line = br.readLine();
				list.add(line);
				line = br.readLine();

			}
		} finally

		{
			br.close();
			list.remove(list.size() - 1);
			for (String ele : list) {
				// System.out.println(ele);
				new Formatter(outPath).formatter(ele);
			}

		}
	}
}
