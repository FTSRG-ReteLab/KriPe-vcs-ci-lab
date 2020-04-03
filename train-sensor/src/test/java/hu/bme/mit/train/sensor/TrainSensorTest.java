package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainController controllerMock;
	TrainUser user;
	TrainSensor sensor;
	
    @Before
    public void before() {
    	controllerMock = mock(TrainController.class);
    	user = new TrainUserImpl(controllerMock);
		sensor = new TrainSensorImpl(controllerMock, user);
    }

    @Test
    public void speedLimitIsUnderAbsoluteMinimum() {
    	sensor.overrideSpeedLimit(-1);
    	assertTrue(user.getAlarmState());
    }
    
    @Test
    public void speedLimitIsOverAbsoluteMaximum() {
    	sensor.overrideSpeedLimit(501);
    	assertTrue(user.getAlarmState());
    }
    
    @Test
    public void speedLimitIsMoreThan50PctSlower() {
        when(controllerMock.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(40);
    	assertTrue(user.getAlarmState());
    }
    
    @Test
    public void speedLimitIsExactly50PctSlower() {
        when(controllerMock.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(50);
    	assertFalse(user.getAlarmState());
    }
    
}
