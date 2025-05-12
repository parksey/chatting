const APP_BASE_URL = "http://localhost:8080/app";

export const fetchChatRooms = async () => {
    const response = await fetch(`${APP_BASE_URL}/rooms`);
    if (!response.ok) {
      throw new Error('Failed to fetch chat rooms');
    }
    return response.json();
  };