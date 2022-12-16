package Libreria;

public interface IComando {
    default String getName(String name) {
        return name;
    }

    void execute(EventData event);

    void execute(EventData event, boolean skip);
}
