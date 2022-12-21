package Libreria;

public interface IComando {

    void execute(EventData event);

    void execute(EventData event, boolean skip);
}
