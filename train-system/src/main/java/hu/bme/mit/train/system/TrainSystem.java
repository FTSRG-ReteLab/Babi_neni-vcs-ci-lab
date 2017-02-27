package hu.bme.mit.train.system;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.Database;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

import java.time.LocalDateTime;

public class TrainSystem implements Database {

	private TrainController controller = new TrainControllerImpl(this);
	private TrainSensor sensor = new TrainSensorImpl(controller);
	private TrainUser user = new TrainUserImpl(controller);

	private Table<LocalDateTime, Integer, Integer> tachograph = HashBasedTable.create();

	public void save(LocalDateTime time, Integer joyStickPosition, Integer speed){
		tachograph.put(time, joyStickPosition, speed);
	}

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

}
