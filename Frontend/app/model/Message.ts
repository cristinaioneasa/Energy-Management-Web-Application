export class Message {

  senderId: number;
  receiverId: number;
  message: string;


  constructor(from: number, to: number, text: string, seen: boolean) {
    this.senderId = from;
    this.receiverId = to;
    this.message = text;
  }

}
