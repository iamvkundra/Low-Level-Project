package service;

public interface Subscriber {

    void register(String regex, Consumer consumer);

    void deRegister(String regex, Consumer consumer);

    void notifySubscriber(String regex, String data);
}
