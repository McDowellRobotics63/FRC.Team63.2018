
import wpilib
from wpilib.command.subsystem import Subsystem
from wpilib import Solenoid
from wpilib import Spark

import RobotMap
from infraredsensor import InfraredSensor


class ClawSubsystem (Subsystem):

    def __init__(self):
        super().__init__("Claw")

        self.leftMotor = Spark(RobotMap.CLAWLEFT)
        self.rightMotor = Spark(RobotMap.CLAWRIGHT)

        self.CLAW_OPEN = Solenoid(RobotMap.PCM1_CANID,RobotMap.CLAW_OPEN_SOLENOID)
        self.CLAW_CLOSE = Solenoid(RobotMap.PCM1_CANID,RobotMap.CLAW_CLOSE_SOLENOID)

        self.sensor = InfraredSensor(RobotMap.INFRARED_SENSOR_CHANNEL)

        self.close()
	
    #def initDefaultCommand(self):
        #self.setDefaultCommand(ObtainBoxContinuous())
    
    def open(self):
        self.CLAW_OPEN.set(True)
        self.CLAW_CLOSE.set(False)
    
    def close(self):
        self.CLAW_OPEN.set(False)
        self.CLAW_CLOSE.set(True)
    
    def setSpeed(self, pullSpeed):
        #positive left speed is pull
        self.leftMotor.set(pullSpeed)
        self.rightMotor.set(-pullSpeed)
    
    def boxIsClose(self):
        return self.sensor.GetMedianVoltage() > 0.6
    
    def boxIsReallyClose(self):
        return self.sensor.GetMedianVoltage() > 2.6
