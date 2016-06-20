package MachineLearningTraining;

import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {
		new MLtraining("/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5.tsv",
				"/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Pair5(new).txt",
				"/Users/AlanHo/Documents/DissertationLibrary/NER (ML training)/Training data/Training5.txt");

	}
}
