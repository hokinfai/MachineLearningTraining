package MachineLearningTraining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NewInputFormatter {

	public static void main(String args[]) throws IOException {
		StringBuilder output = new StringBuilder();
		String txtPath = "/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5.txt";
		BufferedReader br = new BufferedReader(new FileReader(txtPath));
		try

		{

			String line = br.readLine();

			while (line != null) {

				// System.out.println("new term: " + line);
				if (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))
					line = br.readLine();
				String[] spilt = line.split("\t");
				if (spilt[2].matches(".+-.+") && spilt[2].matches(".+\\..+")) {
					// System.out.println("true");
					int index = spilt[2].indexOf("-");
					int middleIndex = (Integer.parseInt(spilt[0]) + index);
					StringBuilder newString = new StringBuilder();
					newString.append(spilt[0] + "\t" + "" + middleIndex + "\t" + spilt[2].substring(0, index) + "\t"
							+ spilt[3].substring(0, index) + "\t");
					if (spilt[2].substring(0, index).matches("\\d+"))
						newString.append("CD\t");
					else
						newString.append(spilt[4] + "\t");
					newString.append(spilt[5] + "\n");
					newString.append(
							middleIndex + "\t" + (middleIndex + 1) + "\t-\t-\tHYPT\tI" + spilt[5].substring(1) + "\n");
					newString.append((middleIndex + 1) + "\t" + spilt[1] + "\t" + spilt[2].substring(index + 1) + "\t"
							+ spilt[2].substring(index + 1) + "\t" + spilt[4] + "\tI" + spilt[5].substring(1) + "\n");
					// System.out.println(newString);
					StringBuilder ans = new StringBuilder();
					String[] sentence = newString.toString().split("\n");
					for (String ele : sentence) {
						if (ele.contains(".")) {
							String[] token = ele.split("\t");
							int docInd = token[2].indexOf(".");

							ans.append(token[0] + "\t" + (Integer.parseInt(token[0]) + docInd) + "\t"
									+ token[2].substring(0, docInd) + "\t" + token[2].substring(0, docInd) + "\tCD\t"
									+ token[5] + "\n");
							ans.append((Integer.parseInt(token[0]) + docInd) + "\t"
									+ (Integer.parseInt(token[0]) + docInd + 1) + "\t.\t.\t.\tI-NP\n");
							ans.append((Integer.parseInt(token[0]) + docInd + 1) + "\t" + token[1] + "\t"
									+ token[2].substring(docInd + 1) + "\t" + token[2].substring(docInd + 1)
									+ "\tCD\tI-NP\n");
						} else {
							ans.append(ele + "\n");

						}
					}
					System.out.println(ans);

					output.append(ans.toString());
				} else if (spilt[2].matches(".+-.+")) {
					int index = spilt[2].indexOf("-");
					int middleIndex = (Integer.parseInt(spilt[0]) + index);
					StringBuilder newString = new StringBuilder();
					newString.append(spilt[0] + "\t" + "" + middleIndex + "\t" + spilt[2].substring(0, index) + "\t"
							+ spilt[3].substring(0, index) + "\t");
					if (spilt[2].substring(0, index).matches("\\d+"))
						newString.append("CD\t");
					else
						newString.append(spilt[4] + "\t");
					newString.append(spilt[5] + "\n");
					newString.append(
							middleIndex + "\t" + (middleIndex + 1) + "\t-\t-\tHYPT\tI" + spilt[5].substring(1) + "\n");
					newString.append((middleIndex + 1) + "\t" + spilt[1] + "\t" + spilt[2].substring(index + 1) + "\t"
							+ spilt[2].substring(index + 1) + "\t" + spilt[4] + "\tI" + spilt[5].substring(1) + "\n");
					System.out.println(newString);
					output.append(newString.toString());
				} else {
					System.out.println(line);
					output.append(line + "\n");
				}

				line = br.readLine();
			}

		} finally

		{
			br.close();
			File file = new File("/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5(new).txt");
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);
			// Writes the content to the file
			writer.write(output.toString());
			writer.flush();
			writer.close();

		}

	}

}
