package org.example.demo1;

import org.example.demo1.entity.MatchHistory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockWebSocketClient {
    private static final String[] RESULTS = {"Win", "Lose", "Draw"};
    private static final Random RANDOM = new Random();

    public List<MatchHistory> fetchMatchHistory() {
        // Giả lập dữ liệu từ server
        List<MatchHistory> matchHistories = new ArrayList<>();

        for (int i = 1; i <= 25; i++) {
            long userId = 1000 + i; // Giả lập User ID từ 1001 đến 1025
            long matchId = 2000 + i; // Giả lập Match ID từ 2001 đến 2025
            String result = RESULTS[RANDOM.nextInt(RESULTS.length)]; // Chọn ngẫu nhiên kết quả
            int pointsEarned = RANDOM.nextInt(100); // Điểm kiếm được ngẫu nhiên từ 0 đến 99
            LocalDateTime createdAt = LocalDateTime.now().minusDays(RANDOM.nextInt(30)); // Thời gian tạo ngẫu nhiên trong 30 ngày

            matchHistories.add(new MatchHistory(i, userId, matchId, result, pointsEarned, createdAt));
        }

        return matchHistories;
    }
}
