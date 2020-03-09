package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);
	private Tachograph tachograph = new Tachograph();

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}
	
	public void test_stdout() {
	  System.out.println("Hello world!");
	}

	public static class Tachograph
	{
		//    Time    Pos     Speed
		Table<Double, Double, Double> data = HashBasedTable.create();

		Tachograph() {
			data.put(0.0, -1.0, -10.0);
			data.put(1.0, 1.0, 10.0);
		}

		public int getSize() {
			return data.size();
		}
	}
}
