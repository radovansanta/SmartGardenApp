package com.example.smartgarden.Models;

import java.util.Optional;

public class Log {
        private Command command;
        private Valve valve;

        public Log(Command command, Valve valve){
            this.command=command;
            this.valve=valve;
        }

        public void setCommand(Command command)
        {
            this.command = command;
        }

        public void setValve(Valve valve)
        {
            this.valve = valve;
        }

        public Command getCommand()
        {
            return command;
        }

        public Valve getValve()
        {
            return valve;
        }
}
