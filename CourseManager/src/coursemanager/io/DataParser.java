package coursemanager.io;

public interface DataParser<T> {
    String PROPERTY_SEPARATOR = "; ";
    T parse(String data);
}
