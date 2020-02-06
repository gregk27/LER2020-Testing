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
public static enum Mappings{
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





