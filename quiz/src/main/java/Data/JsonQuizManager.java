package Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Models.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonQuizManager implements DataManager<Quiz<String>> {
    private static final String FILE_PATH = "quiz_data.json";
    private final Gson gson;
    private final ReadWriteLock lock;
    private List<Quiz<String>> quizzes;

    public JsonQuizManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.lock = new ReentrantReadWriteLock();
        this.quizzes = new ArrayList<>();
        initializeFile();
    }

    private void initializeFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                saveAll(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize quiz data file", e);
        }
    }

    @Override
    public List<Quiz<String>> loadAll() {
        lock.readLock().lock();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() || file.length() == 0) {
                this.quizzes = new ArrayList<>();
                return new ArrayList<>();
            }

            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<ArrayList<Quiz<String>>>(){}.getType();
                List<Quiz<String>> loaded = gson.fromJson(reader, listType);
                this.quizzes = loaded != null ? loaded : new ArrayList<>();
                return new ArrayList<>(quizzes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load quizzes", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void saveAll(List<Quiz<String>> items) {
        lock.writeLock().lock();
        try {
            this.quizzes = new ArrayList<>(items);
            persistToFile();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Quiz<String>> findById(String id) {
        lock.readLock().lock();
        try {
            return quizzes.stream()
                    .filter(q -> q.getQuizId().equals(id))
                    .findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void addItems(Quiz<String> quiz) {
        lock.writeLock().lock();
        try {
            quizzes.add(quiz);
            persistToFile();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void updateItems(String id, Quiz<String> quiz) {
        lock.writeLock().lock();
        try {
            for (int i = 0; i < quizzes.size(); i++) {
                if (quizzes.get(i).getQuizId().equals(id)) {
                    quizzes.set(i, quiz);
                    persistToFile();
                    return;
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void deleteItems(String id) {
        lock.writeLock().lock();
        try {
            quizzes.removeIf(q -> q.getQuizId().equals(id));
            persistToFile();
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void persistToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(quizzes, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save quizzes to file", e);
        }
    }
}