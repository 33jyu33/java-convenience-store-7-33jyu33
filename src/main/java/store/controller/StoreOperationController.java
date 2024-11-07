package store.controller;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StoreOperationController {
    public void run() {

    }


    private List<String> readProduct() {
        final String READ_FILE_ERROR = "Product.md 파일을 읽는 데 에러 발생, 확인 요망.";
        try {
            return Files.readAllLines(Paths.get("C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\products.md"));
        } catch (IOException e) {
            System.out.println(READ_FILE_ERROR);
            return null;
        }
    }
}
