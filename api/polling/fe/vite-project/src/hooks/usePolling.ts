import { useEffect, useState } from 'react';
import type { Message } from '../types/chat';
import { fetchMessages } from '../services/pollingApi';

export const usePolling = (roomId: number, interval: number = 3000) => {
  const [data, setData] = useState<Message[]>([]);
  const [error, setError] = useState<Error | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [cursorId, setCursorId] = useState<number | null>(null);

  useEffect(() => {
    let isMounted = true;

    const fetchData = async () => {
      try {
        const result = await fetchMessages(roomId, cursorId);
        
        if (isMounted) {
          setData(result.messages);
          if (result.messages.length > 0) {
            setCursorId(result.messages[result.messages.length - 1].id);
          }
          setError(null);
        }
      } catch (err) {
        if (isMounted) {
          setError(err as Error);
        }
      } finally {
        if (isMounted) {
          setIsLoading(false);
        }
      }
    };

    const intervalId = setInterval(fetchData, interval);
    fetchData(); // 초기 데이터 로드

    return () => {
      isMounted = false;
      clearInterval(intervalId);
    };
  }, [roomId, cursorId, interval]);

  return { data, error, isLoading };
};