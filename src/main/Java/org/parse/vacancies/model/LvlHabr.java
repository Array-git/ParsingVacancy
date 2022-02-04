package org.parse.vacancies.model;

public enum LvlHabr {
    NONE(""), Trainee("qid=1"), Junior("qid=2")
    , Middle("qid=3"), Senior("qid=4"), Lead("qid=5");
    private String stringLVL;

    LvlHabr(String stringLVL) {
        this.stringLVL = stringLVL;
    }

    public String getStringLVL() {
        return stringLVL;
    }
}
