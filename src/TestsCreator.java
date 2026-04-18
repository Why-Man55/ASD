import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestsCreator {
    public static void main(String[] args){
        Random rd = new Random();
        int iterationsCount = 50;
        try(FileWriter file = new FileWriter("src/tests.txt", false)){
            for (int i = 1; i <= iterationsCount; i++){
                int cnt = rd.nextInt(100, 10001);
                for (int j = 1; j <= cnt; j++){
                    file.write("" + rd.nextInt(1, 10001));
                    if (j != cnt){
                        file.write(" ");
                    }
                }
                file.append('\n');
            }
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}