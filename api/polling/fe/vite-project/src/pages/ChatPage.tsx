import { useParams } from 'react-router-dom';
import { ChatRoom } from '../components/chat/ChatRoom';

export const ChatPage = () => {
  const { roomId } = useParams<{ roomId: string }>();
  
  if (!roomId) {
    return <div>Invalid room ID</div>;
  }

  return <ChatRoom roomId={parseInt(roomId, 10)} />;
};