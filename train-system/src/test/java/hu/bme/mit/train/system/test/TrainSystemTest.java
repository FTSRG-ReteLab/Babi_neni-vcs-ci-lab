package hu.bme.mit.train.system.test;

import hu.bme.mit.train.interfaces.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {
	TrainSystem trainSystem;
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		trainSystem = new TrainSystem();
		controller = trainSystem.getController();
		sensor = trainSystem.getSensor();
		user = trainSystem.getUser();

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void test1() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void test2() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
        controller.followSpeed();
        Assert.assertEquals(-1, controller.getReferenceSpeed());
	}

	@Test
	public void test3() {
		controller.setDirection(Direction.LEFT);
		controller.setDirection(null);
		Assert.assertEquals(Direction.LEFT, controller.getDirection());
	}

	@Test
	public void test4() {
		controller.followSpeed();
		controller.followSpeed();
		controller.followSpeed();

		Assert.assertEquals(3, trainSystem.getTachograph().size());
	}

	
}
