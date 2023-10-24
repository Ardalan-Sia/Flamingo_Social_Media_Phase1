package Models;

import a.CLI;

public class Comment extends Tweet{

    public Comment(){
    }

    public Comment(String body ,User user) {
        super(body,user);

    }


    @Override
    public String toString() {
        return   time +
                " @" + CLI.getLogic().idToUsername(userId) +
                " "+ body +
                ", likes : " + likes.size() +
                ", replies : " + comments.size();

    }
}
