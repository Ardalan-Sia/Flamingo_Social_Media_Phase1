package Models;

public class Like {
    private int userId;

    public Like() {
    }

    public Like(User user) {
        this.userId = user.getId();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
