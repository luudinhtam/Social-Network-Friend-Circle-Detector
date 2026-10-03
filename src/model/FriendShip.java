
package model;

import java.util.ArrayList;


public class FriendShip {
    private String userID;
    ArrayList<User> friends = new ArrayList<>();

    public FriendShip(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    
    
    
}
