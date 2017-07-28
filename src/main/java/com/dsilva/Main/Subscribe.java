package com.dsilva.Main;

import com.dsilva.BrokerDetails.Broker;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Himasha on 7/28/2017.
 */
public class Subscribe implements MqttCallback {
    Broker broker = new Broker();
    private MqttClient client;

    public Subscribe(boolean cleanSession, String clientId, String topic) throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(cleanSession);
        options.setUserName(broker.USER_NAME);
        options.setPassword(broker.PASSWORD.toCharArray());

        MemoryPersistence persistence = new MemoryPersistence();

        client.setCallback(this);
        client.connect(options);
        client.subscribe(topic);


    }

    public void close(){
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void connectionLost(Throwable throwable) {

    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(mqttMessage.toString());
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
