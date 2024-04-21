package implmentation;

import service.Consumer;

public class ConsumerImpl implements Consumer {

    public String data;

    public ConsumerImpl(String data) {
        this.data = data;
    }

    @Override
    public void consume(String news) {
        System.out.println(data + " received news: " + news);
    }
}
