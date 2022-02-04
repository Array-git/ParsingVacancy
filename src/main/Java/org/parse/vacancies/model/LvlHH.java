package org.parse.vacancies.model;

public enum LvlHH {
    NONE(""), Trainee("trainee"), Junior("junior")
    , Middle("middle")
    , Senior("senior"), Lead("lead");
    private String stringLVL;

    LvlHH(String stringLVL) {
        this.stringLVL = stringLVL;
    }

    public String getStringLVL() {
        return stringLVL;
    }
}
