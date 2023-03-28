package edu.kis.powp.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements DriverCommand{
    private final DriverCommand[] commands;
    public ComplexCommand(DriverCommand[] commands) {
        this.commands = commands;
    }
    public void execute(Job2dDriver driver) {
        for(DriverCommand command : commands) {
            command.execute(driver);
        }
    }

}

