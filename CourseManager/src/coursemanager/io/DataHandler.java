package coursemanager.io;

import coursemanager.model.CommonList;

import java.nio.file.Path;

public interface DataHandler<T> {
    public CommonList<T> readFile(Path path);
    public void saveFile(Path path, CommonList<T> data);
}
