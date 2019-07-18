package in.saeakgec.ebike.listener;

public interface BikesAdapterListener {
    void shareActivity(String id);
    void turnOn(String id);
    void turnOff(String id);
    void settingActvity();
}
