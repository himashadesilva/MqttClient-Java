package com.dsilva.Main;

import com.dsilva.BrokerDetails.Broker;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Himasha on 7/28/2017.
 */
public class Publish {
    Broker broker = new Broker();
    private MqttClient client;

    public Publish(boolean cleanSession, String clientId) throws MqttException {
        MqttConnectOptions option = new MqttConnectOptions();
        option.setCleanSession(cleanSession);
        option.setUserName(broker.USER_NAME);
        option.setPassword(broker.PASSWORD.toCharArray());

        MemoryPersistence persistence = new MemoryPersistence();

        client = new MqttClient(broker.BROKER, clientId, persistence);
        client.connect(option);
    }

    public void sendMessage(String topic, String msg) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic, message);
    }

    public void close() throws MqttException {
        System.out.println("closing connections...");
        client.disconnect();
        client.close();
        System.out.println("connection closed");
    }
}
