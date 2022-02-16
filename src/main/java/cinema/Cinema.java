package cinema;

import java.util.Scanner;

public class Cinema {
    final static int tenPrice = 10;
    final static int eigthPrice = 8;
    static int rows;
    static int seats;
    static int allPlaces;
    static int purchasedPlaces;
    static int currentIncome;
    static int totalIncome;
    final static char FREE_SEAT = 'S';
    final static char BUY_SEAT = 'B';
    static char[][] cinema;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        getCinemaSize();
        while (showMenu());
    }

    private static void initCinema() {
        allPlaces = rows * seats;
        cinema = new char[rows + 1][seats + 1];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = ' ';
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j).charAt(0);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i).charAt(0);
                } else {
                    cinema[i][j] = FREE_SEAT;
                }
            }
        }

        if (rows * seats < 60) {
            totalIncome = seats * rows * tenPrice;
        } else {
            int half = rows / 2;
            if (rows % 2 == 0) {
                totalIncome = seats * half * (tenPrice + eigthPrice);
            } else {
                totalIncome = seats * ((half + 1) * eigthPrice + half * tenPrice);
            }
        }
    }

    private static boolean showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int number = scanner.nextInt();
        boolean status = true;
        switch (number) {
            case 1:
                showCinema();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                showStatistics();
                break;
            case 0:
                status = false;
                break;
        }
        System.out.println();
        return status;
    }

    private static void buyTicket() {
        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row > rows || seat > seats) {
                System.out.println("Wrong input!\n");
                continue;
            }
            if (cinema[row][seat] == BUY_SEAT) {
                System.out.println("That ticket has already been purchased!\n");
                continue;
            }
            cinema[row][seat] = BUY_SEAT;
            purchasedPlaces++;
            int price;
            if (rows * seats < 60) {
                price = tenPrice;
            } else {
                price = row <= rows / 2 ? tenPrice : eigthPrice;
            }
            currentIncome += price;
            System.out.printf("Ticket price: $%d\n", price);
            System.out.println();
            break;
        }

    }

    private static void showStatistics() {
        System.out.printf("Number of purchased tickets: %d\n", purchasedPlaces);
        System.out.printf("Percentage: %.2f%c\n", 1.0f * purchasedPlaces / allPlaces * 100, '%');
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
        System.out.println();
    }

    private static void getCinemaSize() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        initCinema();
        System.out.println();
    }

    private static void showCinema() {
        System.out.println("Cinema:");
        for (char[] chars : cinema) {
            for (int j = 0; j < chars.length; j++) {
                System.out.printf("%c ", chars[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}