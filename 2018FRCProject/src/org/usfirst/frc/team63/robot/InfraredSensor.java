package org.usfirst.frc.team63.robot;
import java.util.Collections;
import java.util.TimerTask;
import java.util.Vector;

import edu.wpi.first.wpilibj.AnalogInput;


public class InfraredSensor extends AnalogInput {

	public static final int SAMPLE_SIZE = 11;
	public static final int MEDIAN_ELEMENT = 6;
	public Vector<Double> SampleList;
	private java.util.Timer SampleTimer;
	
	public InfraredSensor(int channel) {
		super(channel);
		SampleTimer = new java.util.Timer();
		SampleList = new Vector<Double>();
		SampleList.clear();
		SampleTimer.schedule(new SampleTask(this), 0L, (long) (20));		
	}

	public double GetVoltage()
	{		
		return super.getVoltage();		
	}
	
	public double GetMedianVoltage()
	{				
		Vector<Double> ListCopy  = new Vector<Double>(SampleList);
		Collections.sort(ListCopy);
		return ListCopy.elementAt(MEDIAN_ELEMENT);
	}

    private class SampleTask extends TimerTask {

        private InfraredSensor m_controller;

        public SampleTask(InfraredSensor controller) {
            if (controller == null) {
                throw new NullPointerException("Given PressureSensor was null");
            }
            m_controller = controller;
        }

        @Override
    public void run() {
            m_controller.SampleList.insertElementAt(m_controller.GetVoltage(), 0);
            for(int i = m_controller.SampleList.size()-1; i >=SAMPLE_SIZE; i--)
            {
            	m_controller.SampleList.removeElementAt(i);
            }            
            //System.out.println("Sample List Size: " + m_controller.SampleList.size());
        }
    }
}
