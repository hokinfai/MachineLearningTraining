package MachineLearningTraining;

import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {
		new MLtraining("/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair2.tsv",
				"/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair2(new).txt",
				"/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Training data/Training2.txt");

	}
}
