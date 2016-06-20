package MachineLearningTraining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MLtraining {
	String tsvPath;
	String txtPath;
	String trainingPath;
	List<String> tsvlist = new ArrayList<String>();
	List<String> txtlist = new ArrayList<String>();
	StringBuilder ans = new StringBuilder();
	StringBuilder checking = new StringBuilder();
	int index = 0;

	public MLtraining(String tsvPath, String txtPath, String training) throws IOException {
		this.tsvPath = tsvPath;
		this.txtPath = txtPath;
		trainingPath = training;
		BufferedReader br = new BufferedReader(new FileReader(tsvPath));
		BufferedReader br2 = new BufferedReader(new FileReader(txtPath));
		try

		{
			// insert tsv file record into arraylist
			String line = br.readLine();

			while (line != null) {
				if (checking.toString().contains(line)) {
					// System.out.println("failed:" + line);
					line = br.readLine();
				} else {
					// System.out.println("new term: " + line);
					tsvlist.add(line);

					checking.append(line);
					line = br.readLine();
				}
			}

			// insert txt file record into arraylist
			String line2 = br2.readLine();
			while (line2 != null) {

				txtlist.add(line2);
				// System.out.println(line2);
				line2 = br2.readLine();

			}

		} finally

		{
			br.close();
			br2.close();
			merge();
			File file = new File(trainingPath);
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);
			// Writes the content to the file
			writer.write(ans.toString());
			writer.flush();
			writer.close();
		}
	}

	public void merge() {
		tsvlist.add("0\t0\t \t \n");
		for (String line : txtlist) {
			String[] spiltLine = line.split("\t");
			String[] spilTsv = tsvlist.get(index).split("\t");
			int tsvBegin = Integer.parseInt(spilTsv[0]);
			int lineBegin = Integer.parseInt(spiltLine[0]);
			System.out.println(tsvBegin);
			System.out.println(lineBegin);
			if ((tsvBegin - lineBegin < 3 || lineBegin - tsvBegin < 3) && spilTsv[2].trim().equals(spiltLine[2].trim())) {
				System.out.println(spilTsv[3] + "\t" + tsvlist.get(index));
				ans.append(spilTsv[3] + "\t" + line + "\n");
				index++;
			} else {
				System.out.println("O\t" + line);
				ans.append("O\t" + line + "\n");
			}
		}
	}
}
