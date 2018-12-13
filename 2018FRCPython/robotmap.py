
import math

#Talon CAN IDs
DRIVELEFTMASTER = 3
DRIVELEFTSLAVE = 4
DRIVERIGHTMASTER = 1
DRIVERIGHTSLAVE = 2
LIFT = 5

#Spark PWM Channels
CLAWLEFT = 6
CLAWRIGHT = 7
CLIMBWINCH = 8

#Solenoid Mappings
PCM1_CANID = 6
CLIMBARM_UP = 1
CLIMBARM_DOWN = 3
CLIMBCLAMPLOCK_OPEN = 6
CLIMBCLAMPLOCK_CLOSE = 7
CLAW_CLOSE_SOLENOID = 2
CLAW_OPEN_SOLENOID = 0
SHIFTER_HIGH = 4
SHIFTER_LOW = 5

#Claw
BOX_IN_SPEED = -0.4
BOX_OUT_SPEED = 0.4
INFRARED_SENSOR_CHANNEL = 0

#Climb
CLIMB_UP_SPEED = -1.0
CLIMB_DOWN_SPEED = 1.0

#Digital Inputs
AUTO_SWITCH_1 = 0
AUTO_SWITCH_2 = 1
AUTO_SWITCH_3 = 2
LIMIT_SWITCH_HOOK = 4
LIMIT_SWITCH_LIFT = 3

TIMEOUT_MS = 10 #ms to wait for talon timeout, literally doesn't matter

LIFT_INCHES_PER_UNIT = 0.000033

#Drive constants
DRIVE_WHEEL_DIAMETER = 6
DRIVE_WHEEL_CIRCUMFERENCE = DRIVE_WHEEL_DIAMETER*math.pi #inches
DRIVE_TRACK = 25 #inches between centerlines of left and right wheels
DRIVE_ENCODER_PPR = 4096 #native units per rotation
TIMOUT_MS = 10 #ms to wait for talon timeout, literally doesn't matter
VELOCITY_CONTROL_SLOT = 0
DRIVE_F = 0.5683
DRIVE_P = 0.5
DRIVE_I = 0.003
DRIVE_D = 35.0
DRIVE_IZONE = 200
DRIVE_CRUISE = 1600
DRIVE_ACCEL = 1600
DRIVE_RIZONE = 200
DRIVE_RCRUISE = 800
DRIVE_RACCEL = 1600
