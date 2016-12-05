package com.adventofcode.year2016.day5;

class Password {
    private final char[] password;

    public Password(int size) {
        this.password = new char[size];
    }

    public Password(char[] password) {
        this.password = password;
    }

    public Password withCharacter(CharacterAtPosition cap) {
        char[] newPassword = password.clone();
        newPassword[cap.position] = cap.character;
        return new Password(newPassword);
    }

    public static Password merge(Password a, Password b) {
        char[] c = new char[a.password.length];
        for (int i = 0; i < a.password.length; i++) {
            c[i] = (a.password[i] == 0 ? b.password[i] : a.password[i]);
        }
        return new Password(c);
    }

    public String asString() {
        return String.valueOf(password);
    }
}