import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class PrintToCSV {

	public static void printToCSVFile(List<LipstickModel> lipsticks) {

		String CSV_SEPARATOR = ",";
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("lipstickdetails.csv")));

			StringBuffer columnValues = new StringBuffer();
			columnValues.append("Product Name");
			columnValues.append(CSV_SEPARATOR);
			columnValues.append("Product Id");
			columnValues.append(CSV_SEPARATOR);
			columnValues.append("Product Price");
			columnValues.append(CSV_SEPARATOR);
			columnValues.append("Product Image URL");
			columnValues.append(CSV_SEPARATOR);
			columnValues.append("Product Description");
			bw.write(columnValues.toString());
			bw.newLine();

			for (LipstickModel currentLipstick : lipsticks) {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(currentLipstick.getProdName());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(currentLipstick.getProdId());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(currentLipstick.getProdPrice());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(currentLipstick.getProdImageURL());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append("\"" + currentLipstick.getProdDescription() + "\"");
				bw.write(oneLine.toString());
				bw.newLine();

			}

			bw.flush();
			bw.close();

		} catch (IOException e) {
		}

	}
}