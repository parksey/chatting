import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ChatRoomListPage } from './pages/ChatRoomListPage';
import { ChatPage } from './pages/ChatPage';

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<ChatRoomListPage />} />
      <Route path="/chat/:roomId" element={<ChatPage />} />
    </Routes>
    </BrowserRouter>
    // <div className="app">
    //   <ChatPage />
    // </div>
  );
}

export default App;