import java.io.*;

public class ConvertFile {
    public static void main(String[] args) {
        // Define input and output file paths
        String inputFile = System.getProperty("user.home") + "/Downloads/input.csv";  // Path to input CSV
        String outputFile = System.getProperty("user.home") + "/Downloads/output.arff";  // Path to output ARFF

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line = br.readLine();  // Read the first line (header)
            if (line == null) {
                System.out.println("Error: Empty CSV file.");
                return;
            }

            String[] attributes = line.split(",");  // Split header into attributes

            // Writing ARFF Header
            bw.write("@RELATION dataset\n\n");
            for (String attribute : attributes) {
                bw.write("@ATTRIBUTE " + attribute.trim() + " STRING\n");  // Defaulting to STRING
            }
            bw.write("\n@DATA\n");  // Start of data section

            // Writing Data
            while ((line = br.readLine()) != null) {
                bw.write(line.replace(",", ","));  // Keep data format
                bw.newLine();
            }

            System.out.println("Conversion successful! Output saved as " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
