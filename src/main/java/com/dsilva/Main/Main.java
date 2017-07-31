package com.dsilva.Main;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

/**
 * Mqtt Client - Java
 *
 * @author Himasha De Silva
 */
public class Main {
    private static final String PUBLISH = "publish";
    private static final String SUBSCRIBE = "subscribe";
    private static final String DASH = "-";
    private static final String HELP = "help";
    private static final String MESSAGE = "m";
    private static final String FILE = "f";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Publish or Subscribe?");

        String type = sc.nextLine();
        System.out.println("topic ?");

        String topic = sc.nextLine();
        String message;
        System.out.println("client Id?");
        String clientId = sc.nextLine();

        //if (args[0].split(DASH)[1].equalsIgnoreCase(PUBLISH) && args.length==5)
        if (type.equalsIgnoreCase(PUBLISH))
        {
            try {
                while (true) {
                    System.out.println("messsage :");
                    message = sc.nextLine();
                    Publish publish = new Publish(true, clientId);
                    if(message.equalsIgnoreCase("exit")){
                        publish.close();
                        break;
                    }
                    publish.sendMessage(topic, message);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else if (type.equalsIgnoreCase(SUBSCRIBE)) {
            try {
                Subscribe subscribe = new Subscribe(true, clientId, topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        /*else if(args[0].split(DASH)[1].equalsIgnoreCase(HELP)){
            System.out.println("usage : ");
        }
        else {
            System.out.println("invalid arguments");
        }*/

    }
}
