package FileHandling;

import Quizes.Quiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveAndReadJSON {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().setObjectToNumberStrategy((ToNumberPolicy.LONG_OR_DOUBLE)).create();
    public static void saveQuiz(List<Quiz> quizzes, String fileName){
        try (FileWriter writer = new FileWriter(fileName)){
            gson.toJson(quizzes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Quiz> readQuizJSON(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<ArrayList<Quiz>>() {}.getType();
            List<Quiz> loadedQuizz =  gson.fromJson(reader, listType);
            return loadedQuizz != null ? loadedQuizz : new ArrayList<>();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
