package edu.kis.powp.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand {
    private DriverCommand[] commands;
    public ComplexCommand(DriverCommand[] commands) {
        this.commands = commands;
    }
    public void execute(Job2dDriver driver) {
        for(DriverCommand command : commands) {
            command.execute(driver);
        }
    }

    public void setCommands(DriverCommand[] commands) {
        this.commands = commands;
    }
}

