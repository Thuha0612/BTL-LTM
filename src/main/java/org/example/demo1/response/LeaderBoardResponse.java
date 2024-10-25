package org.example.demo1.response;

import org.example.demo1.entity.Users;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaderBoardResponse {

    private List<LeaderBoardUser> users = new ArrayList<>();

    public LeaderBoardResponse(List<LeaderBoardUser> users) {
        this.users = users;
    }

    public LeaderBoardResponse() {
    }

    public void setUsers(List<LeaderBoardUser> users) {
        this.users = users;
    }

    public List<LeaderBoardUser> getUsers() {
        return users;
    }

    // Lớp LeaderBoardUser kế thừa từ User
    public static class LeaderBoardUser extends Users {
        public LeaderBoardUser(Long id, String name, String avatar, Long points, Date createdAt) {
            super(id, null, null, avatar, name, points, null, createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), null); // Gọi constructor của User
        }
    }
}