package cinema;

import java.util.Scanner;

public class Cinema {
    static int countBuyTicket = 0;
    static float percent = 0;
    static int currentIncome = 0;
    static int totalIncome;
    static int row;
    static int seat;

    static void cinemaSelectMenu(String[][] array) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int chose = scanner.nextInt();
            if (chose == 1) {
                cinemaHolePrint(array);
            } else if (chose == 2) {
                buyTicket(array);
            } else if (chose == 3) {
                statistic();
            } else if (chose == 0) {
                flag = false;

            } else {
                System.out.println("Please enter correct data!");
            }
        }
    }

    static void buyTicket(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowTicket = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatTicket = scanner.nextInt();
        if (rowTicket > row || seatTicket > seat || rowTicket < 1 || seatTicket < 1) {
            System.out.println("Wrong input!");
            return;
        }
        if (array[rowTicket][seatTicket].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyTicket(array);
            return;
        }
        int countSeat = row * seat;
        if (countSeat <= 60) {
            System.out.println("Ticket price: $10");
            currentIncome += 10;
        } else {
            if (array.length / 2 > rowTicket) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else {
                System.out.println("Ticket price: $8");
                currentIncome += 8;
            }
        }
        countBuyTicket++;
        percent = ((countBuyTicket * 100) / (float) countSeat);
        System.out.println();
        array[rowTicket][seatTicket] = "B";
    }

    static void statistic() {
        System.out.printf("Number of purchased tickets: %d%n", countBuyTicket);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    static void saleTotal(int row, int seat) {
        int count = row * seat;

        if (count <= 60) {
            totalIncome = count * 10;
        } else {
            int part1 = (row / 2) * seat * 10;
            int part2 = (row - (row / 2)) * seat * 8;
            totalIncome = part1 + part2;
        }
    }

    static void cinemaHolePrint(String[][] array) {
        System.out.println("Cinema:");
        for (String[] strings : array) {
            for (String element : strings) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    static String[][] cinemaHoleCreating() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        String[][] array = new String[row + 1][seat + 1];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 && j == 0) {
                    array[i][j] = " ";
                } else if (i == 0) {
                    array[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    array[i][j] = String.valueOf(i);
                } else {
                    array[i][j] = "S";
                }
            }
        }
        saleTotal(row, seat);
        return array;
    }

    public static void main(String[] args) {
        cinemaSelectMenu(cinemaHoleCreating());
    }
}