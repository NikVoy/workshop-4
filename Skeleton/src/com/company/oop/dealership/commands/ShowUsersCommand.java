package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {

    private final static String YOU_ARE_NOT_AN_ADMIN = "You are not an admin!";

    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return showAllUsers();
    }

    private String showAllUsers() {
        if (!getVehicleDealershipRepository().getLoggedInUser().isAdmin()) {
            throw new IllegalArgumentException(YOU_ARE_NOT_AN_ADMIN);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("--USERS--").append(System.lineSeparator());
        int counter = 1;
        for (User user : getVehicleDealershipRepository().getUsers()) {
            builder.append(String.format("%d. %s", counter, user.toString()))
                    .append(System.lineSeparator());
            counter++;
        }

        return builder.toString().trim();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
