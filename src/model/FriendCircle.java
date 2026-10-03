
package model;

import java.util.ArrayList;


public class FriendCircle {
    
    private String circleID;
    ArrayList<User> members = new ArrayList<>();

    public FriendCircle(String circleID) {
        this.circleID = circleID;
    }

    public String getCircleID() {
        return circleID;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "FriendCircle{" + "circleID=" + circleID + ", members=" + members + '}';
    }
    
    
    
}
