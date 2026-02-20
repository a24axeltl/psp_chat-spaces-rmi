/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import service.MessengerService;

/**
 *
 * @author W10-Portable
 */
public class ChatData {
    private String nameClient;
    private MessengerService remoteClient;
    private List<String> messagesChat;

    public ChatData(String nameClient, MessengerService remoteClient) {
        this.nameClient = nameClient;
        this.remoteClient = remoteClient;
        this.messagesChat = new ArrayList<>();
    }
    
    /**
     * @return the nameClient
     */
    public String getNameClient() {
        return nameClient;
    }

    /**
     * @param nameClient the nameClient to set
     */
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    
    /**
     * @return the remoteClient
     */
    public MessengerService getRemoteClient() {
        return remoteClient;
    }

    /**
     * @param remoteClient the remoteClient to set
     */
    public void setRemoteClient(MessengerService remoteClient) {
        this.remoteClient = remoteClient;
    }

    /**
     * @return the messagesChat
     */
    public List<String> getMessagesChat() {
        return messagesChat;
    }

    /**
     * @param messagesChat the messagesChat to set
     */
    public void setMessagesChat(List<String> messagesChat) {
        this.messagesChat = messagesChat;
    }
    
    public void addMessageToChat(String message){
        this.messagesChat.add(message);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.remoteClient);
        hash = 89 * hash + Objects.hashCode(this.messagesChat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatData other = (ChatData) obj;
        if (!Objects.equals(this.remoteClient, other.remoteClient)) {
            return false;
        }
        return Objects.equals(this.messagesChat, other.messagesChat);
    }

    @Override
    public String toString() {
        return "ChatData{" + "remoteClient=" + remoteClient + ", messagesChat=" + messagesChat + '}';
    }
}
