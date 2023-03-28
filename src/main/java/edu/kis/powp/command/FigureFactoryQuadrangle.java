package edu.kis.powp.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class FigureFactoryQuadrangle {
    public static ComplexCommand rectangleScript(Job2dDriver driver) {
        return new ComplexCommand(new DriverCommand[]{
                new SetPositionCommand(0, 0),
                new OperateToCommand(0, 100),
                new OperateToCommand(200, 100),
                new OperateToCommand(200, 0),
                new OperateToCommand(0, 0)
        });
    }
    public static ComplexCommand triangleScript(Job2dDriver driver) {
        return new ComplexCommand(new DriverCommand[]{
                new SetPositionCommand(0, 0),
                new OperateToCommand(0, 100),
                new OperateToCommand(100, 0),
                new OperateToCommand(0, 0)
        });
    }
    public static ComplexCommand squareScript(Job2dDriver driver) {
        return new ComplexCommand(new DriverCommand[]{
                new SetPositionCommand(0, 0),
                new OperateToCommand(0, 100),
                new OperateToCommand(100, 100),
                new OperateToCommand(100, 0),
                new OperateToCommand(0, 0)
        });
    }
}
