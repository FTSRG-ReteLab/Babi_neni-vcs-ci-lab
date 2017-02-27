package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setDirection(Direction direction);

	Direction getDirection();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

}
