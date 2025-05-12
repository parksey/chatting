import { useEffect, useState } from 'react';
import { MessageList } from './MessageList/MessageList';
import { ChatInput } from './ChatInput';
import { fetchMessages } from '../../services/pollingApi';
import type { Message } from '../../types';

interface ChatRoomProps {
  roomId: number;
}

export const ChatRoom = ({ roomId }: ChatRoomProps) => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [cursorId, setCursorId] = useState<number | null>(null);

  const loadMessages = async () => {
    try {
      const result = await fetchMessages(roomId, cursorId);
      setMessages(result.messages);
      if (result.messages.length > 0) {
        setCursorId(result.messages[result.messages.length - 1].id);
      }
      setError(null);
    } catch (err) {
      setError(err as Error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    loadMessages();
    // Polling 설정
    const intervalId = setInterval(loadMessages, 3000);
    return () => clearInterval(intervalId);
  }, [roomId]);

  const handleSendMessage = async (content: string) => {
    // TODO: 메시지 전송 API 구현
    console.log('Sending message:', content);
  };

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="chat-room">
      <div className="chat-messages">
        <MessageList messages={messages} />
      </div>
      <ChatInput onSendMessage={handleSendMessage} />
    </div>
  );
};