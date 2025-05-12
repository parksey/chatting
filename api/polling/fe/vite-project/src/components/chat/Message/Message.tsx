import type { Message as MessageType } from '../../../types';

interface MessageProps {
  message: MessageType;
}

export const Message = ({ message }: MessageProps) => {
  return (
    <div className={`message-container ${message.isMe ? 'message-mine' : 'message-other'}`}>
      {!message.isMe && (
        <div className="message-sender">
          <img 
            src={message.sender.thumbnail || '/default-avatar.png'} 
            alt={message.sender.name}
            className="sender-avatar"
          />
          <span className="sender-name">{message.sender.name}</span>
        </div>
      )}
      <div className="message-bubble">
        <div className="message-content">{message.content}</div>
        <div className="message-time">
          {new Date(message.createdAt).toLocaleTimeString()}
        </div>
      </div>
    </div>
  );
};