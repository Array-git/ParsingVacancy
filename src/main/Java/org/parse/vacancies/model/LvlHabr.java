package org.parse.vacancies.model;

public enum LvlHabr {
    NONE(""), Trainee("qid=1"), Junior("qid=3")
    , Middle("qid=4"), Senior("qid=5"), Lead("qid=6");
    private String stringLVL;

    LvlHabr(String stringLVL) {
        this.stringLVL = stringLVL;
    }

    public String getStringLVL() {
        return stringLVL;
    }
}
