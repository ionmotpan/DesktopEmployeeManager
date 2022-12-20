package it.step.Model;

public enum Gender {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        return name();
    }
    public static Gender getGender(String gender) {
        if(gender.equalsIgnoreCase("MALE")){
            return MALE;
        }
        else {
            return FEMALE;
        }
    }
}
