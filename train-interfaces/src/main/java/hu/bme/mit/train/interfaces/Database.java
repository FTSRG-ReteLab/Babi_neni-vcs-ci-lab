package hu.bme.mit.train.interfaces;

import java.time.LocalDateTime;

/**
 * Created by Bruno Englert on 2017.02.27..
 */
public interface Database {
    void save(LocalDateTime time, Integer joyStickPosition, Integer speed);
}
