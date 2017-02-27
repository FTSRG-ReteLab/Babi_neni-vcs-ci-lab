package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.Database;
import hu.bme.mit.train.interfaces.Direction;
import hu.bme.mit.train.interfaces.TrainController;

import java.time.LocalDateTime;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
    private Direction direction = null;
    private Database database = null;

    public TrainControllerImpl(Database database){
        this.database = database;
    }


	@Override
	public Direction getDirection(){
		return direction;
	}

	@Override
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}
        LocalDateTime localDateTime = LocalDateTime.now();
        database.save(localDateTime, referenceSpeed, step);
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
