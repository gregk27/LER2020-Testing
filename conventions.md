# LER Programming Conventions
This document serves to outline the programming conventions to be followed by programmers working on this and other repositories.

## Naming
 - Variable names use `camelCase`
 - Class names use `PascalCase` (like camel, but starting with capital)
 - Constants use `UPPER_CASE`
 
## Motor Controllers
Each motor controller should have a constant containing the CANID, and an object representing the controller. Constants should be defined in `RobotMap.Mappings`, while motor controllers should be defined in the main body.
``` java
//Create a Talon with CANID 1
public enum Mappings{
    public static final int INTAKE_TALON=1;
}
...
final CANTalonSRX intakeTalon = new CANTalonSRX(Mappings.INTAKE_TALON);
```

The object and constant names should be as follows:
``` 
objectName      => CONSTANT_NAME

leftDriveSpark1 => LEFT_DRIVE_SPARK_1
shooterTopTalon => SHOOTER_TOP_TALON
intakeTalon     => INTAKE_TALON
 ```
Each name consists of 3 elements:
- Description: `leftDrive, shooterTop, intake`
- Controller type: `Spark, Talon, Talon`
- Number (where in groups): `leftDriveSpark1, leftDriveSpark2`

Constants use the same name, however in UPPER_CASE. 

## Subsystems constants
Subsystem constants should be placed in the appropriate subsystem. 

Example from conveyor.java:
```java 
public class Conveyor implements Command{
    public static final int INTAKE_SPEED=1;
    public static final int SHOOTER_SPEED=0.75;
```
As these are `public static`, they can be referenced anywhere as `Conveyor.INTAKE_SPEED` or `Conveyor.OUTTAKE_SPEED`.

## OI
The operator interface is used to create and manage buttons.

To create a button, define the number in `OI.ButtonMappings`, and create a `JoystickButton` object to represent it.

```java
public enum ButtonMappings {
    public static final int SLOW_BUTTON = Button.kBumperRight.value,
    public static final int INTAKE_BUTTON = Button.kA.value
}
...
public JoystickButton slowButton = new JoystickButton(controller, ButtonMappings.SLOW_BUTTON)
public JoystickButton intakeButton = new JoystickButton(controller, ButtonMappings.INTAKE_BUTTON)
```

Once a button has been created, there are various ways to use it.

1) Start a command when pressed `button.whenPresssed(new Command());`
2) Start a command when released `button.whenReleased(new Command());`
3) Stop a running command `button.cancelWhenPressed(new Command());`
4) Run a command while it remains held `button.whileHeld(new Command());` 

The button can also be referenced from commands as `Robot.oi.slowButton`, however this should be avoided to for cleanliness.