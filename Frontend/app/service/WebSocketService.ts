import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import {Message} from "../model/Message";


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private stompClient: any;
  private stompChat: any;

  //pt notificarile cu depasirea maxconsmption
  initializeConnection(callback: (message: any) => void): void {
    const socket = new WebSocket('ws://localhost:8087/ws');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, () => {
      console.log("WebSocket for assign2 connected!");
      this.stompClient.subscribe(`/user`, callback);
    });
  }
/*
  initializeConnectionChat(callback: (message: any) => void): void {
    const socket2 = new WebSocket('ws://localhost:8088/ws');
    this.stompChat = Stomp.over(socket2);

    this.stompChat.connect({}, () => {
      console.log("WebSocket for assign3 connected!");
      this.stompChat.subscribe(`/private`, callback);
    });
  }

 */
  closeConnection(): void {
    if (this.stompClient) {
      this.stompClient.disconnect();
    }

    /*
    if (this.stompChat) {
      this.stompChat.disconnect();
    }

     */
  }
/*
  sendMessage(chatMessage: Message) {
    this.stompChat.publish({
      destination: "/app",
      body: JSON.stringify(
        {
          'senderId': chatMessage.senderId,
          'receiverId': chatMessage.receiverId,
          'text': chatMessage.message,
        })
    });
  }


 */

}
