package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainSensorImpl sensor;
    TrainController controller;
    TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void StartAlarmCheckTest(){
        assertFalse(sensor.getAlarmState());
    }

    @Test
    public void GlobalLowLimitAnalyseTest(){
        sensor.overrideSpeedLimit(-5);
        assertTrue(sensor.getAlarmState());
        sensor.overrideSpeedLimit(0);
        assertFalse(sensor.getAlarmState());
        sensor.overrideSpeedLimit(5);
        assertFalse(sensor.getAlarmState());
    }

    @Test
    public void GlobalOverLimitAnalyseTest(){
        sensor.overrideSpeedLimit(505);
        assertTrue(sensor.getAlarmState());
        sensor.overrideSpeedLimit(500);
        assertFalse(sensor.getAlarmState());
        sensor.overrideSpeedLimit(495);
        assertFalse(sensor.getAlarmState());
    }

    @Test
    public void RelativeLimitAnalyseTest(){
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        assertTrue(sensor.getAlarmState());
        sensor.overrideSpeedLimit(100);
        assertFalse(sensor.getAlarmState());
        sensor.overrideSpeedLimit(75);
        assertFalse(sensor.getAlarmState());
    }
}
