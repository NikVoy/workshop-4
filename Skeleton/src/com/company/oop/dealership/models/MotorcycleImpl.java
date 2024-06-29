package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class MotorcycleImpl extends VehicleBase implements Motorcycle {

    public static final int MOTORCYCLE_WHEELS = 2;
    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);

    private String category;

    public MotorcycleImpl(String make, String model, double price, String category) {
        super(make, model, price);
        setCategory(category);
    }

    @Override
    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        int categoryLength = category.length();
        ValidationHelpers.validateIntRange(categoryLength, CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, CATEGORY_LEN_ERR);

        this.category = category;
    }

    @Override
    public int getWheels() {
        return MOTORCYCLE_WHEELS;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("Category: %s", getCategory()))
                .append(System.lineSeparator())
                .append(super.printComments());

        return result.toString();
    }
}
