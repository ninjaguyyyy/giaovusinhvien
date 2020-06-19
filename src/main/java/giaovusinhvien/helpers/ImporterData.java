package giaovusinhvien.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ImporterData {
	static BufferedReader inputFile;
    public ImporterData() {
		
    }
	public static boolean importLop(File fileInput) throws IOException {
		inputFile = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream(fileInput), StandardCharsets.UTF_8
	            )
	    );
	    String line;
	    line = inputFile.readLine();
	    while ((line = inputFile.readLine()) != null) {
            String[] splitedLine = line.split(",");
            System.out.println(splitedLine[1]);
//            Student importStudent = new Student(
//                    Integer.parseInt(splitedLine[0]),
//                    splitedLine[1],
//                    Double.parseDouble(splitedLine[2]),
//                    splitedLine[3],
//                    splitedLine[4],
//                    splitedLine[5]
//            );
//            addStudent(importStudent);
        }
		return true;
	}
}
