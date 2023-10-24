package assignment3application;

import com.example.ui.Assignment3Console;

import java.io.IOException;
import java.sql.SQLException;

public class assignment3application {
    public static void main(String[] args) throws IOException, SQLException {
        Assignment3Console lc = new Assignment3Console();
        lc.preStart();
        lc.start();
    }
}