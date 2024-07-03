package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public abstract class VehicleBase implements Vehicle {

    private final static String COMMENTS_HEADER = "--COMMENTS--";
    private final static String NO_COMMENTS_HEADER = "--NO COMMENTS--";

    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    private static final String MAKE_NAME_LEN_ERR = format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    private static final String MODEL_NAME_LEN_ERR = format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    private static final String PRICE_VAL_ERR = format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);

    private String make;
    private String model;
    private double price;
    private List<Comment> comments;

    public VehicleBase(String make, String model, double price) {
        setMake(make);
        setModel(model);
        setPrice(price);
        comments = new ArrayList<>();
    }

    @Override
    public String getMake() {
        return make;
    }

    private void setMake(String make) {
        int makeLength = make.length();
        ValidationHelpers.validateIntRange(
                makeLength,
                MAKE_NAME_LEN_MIN,
                MAKE_NAME_LEN_MAX,
                MAKE_NAME_LEN_ERR);

        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        int modelLength = model.length();
        ValidationHelpers.validateIntRange(
                modelLength,
                MODEL_NAME_LEN_MIN,
                MODEL_NAME_LEN_MAX,
                MODEL_NAME_LEN_ERR);

        this.model = model;
    }

    @Override
    public abstract int getWheels();

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        ValidationHelpers.validateDecimalRange(price, PRICE_VAL_MIN, PRICE_VAL_MAX, PRICE_VAL_ERR);
        this.price = price;
    }

    @Override
    public abstract VehicleType getType();

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s:", getType()))
                .append(System.lineSeparator())
                .append(String.format("Make: %s", make))
                .append(System.lineSeparator())
                .append(String.format("Model: %s", model))
                .append(System.lineSeparator())
                .append(String.format("Wheels: %d", getWheels()))
                .append(System.lineSeparator())
                .append(String.format("Price: $%s", FormattingHelpers.removeTrailingZerosFromDouble(price)))
                .append(printAdditionalInfo())
                .append(printComments());

        return result.toString();
    }

    protected abstract String printAdditionalInfo();

    protected String printComments() {
        StringBuilder result = new StringBuilder();

        if (getComments().isEmpty()) {
            result.append(NO_COMMENTS_HEADER);
        } else {

            result.append(COMMENTS_HEADER)
                    .append(System.lineSeparator());

            for (Comment comment : comments) {
                result.append(comment.toString());
//                        .append(System.lineSeparator());
            }

            result.append(COMMENTS_HEADER);
        }

        return result.toString();
    }


}
