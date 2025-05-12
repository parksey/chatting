import type { User } from "./user";

export interface ChatRoom {
    id: number;
    name: string;
    participants: User[];
    lastMessage?: Message;
    createdAt: string;
  }

  export interface Message {
    id: number;
    content: string;
    sender: {
      id: number;
      name: string;
      thumbnail: string | null;
    };
    messageType: 'TEXT';
    createdAt: string;
    isDeleted: boolean;
    isMe: boolean;
  }

  export interface MessageResponse {
    messages: Message[];
  }