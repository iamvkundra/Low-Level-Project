package implmentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import service.Consumer;
import service.Subscriber;
import Exception.MessagingQueueException;

public class SubscriberImpl implements Subscriber {
    HashMap<String, Set<Consumer>> subscriberList = new HashMap<>();
    private String data;
    //regex, consumerList
    @Override
    public void register(String regex, Consumer consumer) {
        if (!subscriberList.containsKey(regex)) {
            subscriberList.put(regex, new HashSet<>());
        }
        subscriberList.get(regex).add(consumer);
        System.out.println("Successfully added the Subscriber");
    }

    @Override
    public void deRegister(String regex, Consumer consumer) {
        if (!subscriberList.get(regex).contains(consumer)) {
            throw new MessagingQueueException();
        }
        subscriberList.get(regex).remove(consumer);
        System.out.println("Successfully De-Register the Subscriber");
    }

    @Override
    public void notifySubscriber(String regex, String data) {
        Set<Consumer> consumers = subscriberList.get(regex);
        for (Consumer c : consumers) {
            c.consume(data);
        }
    }

    public void publishData(String regex, String data) {
        this.data = data;
        notifySubscriber(regex, data);
    }
}
