package mq;

import mq.bean.User;
import org.springframework.stereotype.Service;

/**
 * author: getthrough
 * date: 2018/3/21
 * description:
 */
@Service("userService")
public class UserService {
    public void addUser(User user) {
        // do add user
        System.out.println("adding user......");
    }

    public void deleteUser(User user) {
        // do delete user
        System.out.println("deleting user......");
    }
}
