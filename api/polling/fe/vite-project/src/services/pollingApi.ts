import type { MessageResponse } from "../types";

const POLLING_BASE_URL = "http://localhost:8081/polling";


  export const fetchMessages = async (roomId: number, cursorId: number | null, limit: number = 20) => {
    const url = cursorId 
      ? `${POLLING_BASE_URL}/chat/${roomId}/messages?cursorId=${cursorId}&limit=${limit}`
      : `${POLLING_BASE_URL}/chat/${roomId}/messages?limit=${limit}`;
      
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Failed to fetch messages');
    }
    return response.json() as Promise<MessageResponse>;
  };