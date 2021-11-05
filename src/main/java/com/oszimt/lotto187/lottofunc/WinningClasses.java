package com.oszimt.lotto187.lottofunc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WinningClasses {
    WINCLASS_1("Gewinnklasse 1", 6, true),
    WINCLASS_2("Gewinnklasse 2", 6, false),
    WINCLASS_3("Gewinnklasse 3", 5, true),
    WINCLASS_4("Gewinnklasse 4", 5, false),
    WINCLASS_5("Gewinnklasse 5", 4, true),
    WINCLASS_6("Gewinnklasse 6", 4, false),
    WINCLASS_7("Gewinnklasse 7", 3, true),
    WINCLASS_8("Gewinnklasse 8", 3, false),
    WINCLASS_9("Gewinnklasse 9", 2, true),
    WINCLASS_10("Niete", 0, false);
    @Getter
    private String winClass;
    @Getter
    private int hits;
    @Getter
    private boolean isSuperHit;
}
