package pl.academy.database.utils.main;

import pl.academy.database.utils.HibernaeUtils;

public class Main {
    public static void main(String[] args) {
        HibernaeUtils
                .getInstance()
                .getSessionFactory()
                .close();

    }
}
