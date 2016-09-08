package pl.blackmonday.hornet.ui.items;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public abstract class ItemDecorator<Data, DecoratorInterface> {

    protected final Data data;
    private final ClickReceiver<Data> receiver;

    public ItemDecorator(Data object, ClickReceiver<Data> receiver) {
        this.data = object;
        this.receiver = receiver;
    }

    public abstract void decorate(DecoratorInterface decoratorInterface);

    public boolean equals(ItemDecorator other) {
        return this.data.equals(other.data);
    }

    public void onClick() {
        receiver.onClick(data);
    }

    public interface Ui {
        void setName(String name);
    }

    public interface ClickReceiver<Data> {
        void onClick(Data data);
    }

}
