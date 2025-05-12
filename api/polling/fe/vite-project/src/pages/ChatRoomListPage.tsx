import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { ChatRoom } from '../types/chat';
import { fetchChatRooms } from '../services/appApi';

export const ChatRoomListPage = () => {
  const [rooms, setRooms] = useState<ChatRoom[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loadRooms = async () => {
      try {
        const data = await fetchChatRooms();
        setRooms(data);
      } catch (err) {
        setError(err as Error);
      } finally {
        setIsLoading(false);
      }
    };

    loadRooms();
  }, []);

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="chat-room-list">
      <h1>채팅방 목록</h1>
      <div className="rooms">
        {rooms.map(room => (
          <div 
            key={room.id} 
            className="room-item"
            onClick={() => navigate(`/chat/${room.id}`)}
          >
            <h3>{room.name}</h3>
          </div>
        ))}
      </div>
    </div>
  );
};