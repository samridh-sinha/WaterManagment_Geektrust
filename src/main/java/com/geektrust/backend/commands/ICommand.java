package com.geektrust.backend.commands;

import java.util.List;

public interface ICommand {
    boolean execute(List<String> tokens);
}
