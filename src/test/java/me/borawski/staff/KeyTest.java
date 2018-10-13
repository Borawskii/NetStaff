package me.borawski.staff;

import me.borawski.staff.data.auth.key.StaffKey;

public class KeyTest {

    public static void main(String[] args) {
        StaffKey key = new StaffKey();
        System.out.println(key.nextString());
    }

}
