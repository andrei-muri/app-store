package model;

public enum Categories {
    GAMES,
    PRODUCTIVITY,
    EDUCATION;

    public static Categories wrap(String category) {
        switch(category) {
            case "Productivity" -> {
                return PRODUCTIVITY;
            }
            case "Education" -> {
                return EDUCATION;
            }
            default -> {
                return GAMES;
            }
        }
    }

}
