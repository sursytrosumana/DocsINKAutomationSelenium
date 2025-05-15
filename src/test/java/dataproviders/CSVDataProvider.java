package dataproviders;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {

    @DataProvider(name = "registrationData")
    public static Object[][] readCSVData() throws Exception {
        String path = "src/test/resources/testdata/registration_data.csv";
        CSVReader reader = new CSVReader(new FileReader(path));

        List<Object[]> data = new ArrayList<>();
        String[] line;
        boolean isHeader = true;

        while ((line = reader.readNext()) != null) {
            if (isHeader) { isHeader = false; continue; }
            data.add(line);
        }

        reader.close();
        return data.toArray(new Object[0][]);
    }
}
