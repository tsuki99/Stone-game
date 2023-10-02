import java.util.Scanner;

public class StoneGame {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        int playerStones = 10;
        int computerStones = 10;
        int round = 1;
        boolean playerTurn = true;
        boolean pairOrNot;

        greetings(); // викликання методу з вітанням

        while (playerStones > 0 && computerStones > 0) {
            System.out.println("Раунд " + round + ":");

            if (playerTurn) {
                System.out.println("Ви відгадуєте, парну/непарну кількість камінців сховав комп'ютер. (1 - парна,"
                        + " 2 - непарна)");
                int computerHidden = (computerStones < 4) ? (int) (Math.random() * computerStones) + 1
                        : (int) (Math.random() * 4) + 1;
                System.out.println("Комп'ютер сховав " + computerHidden + declension(computerHidden)); // відображення
                // кількості схованих комп'ютером кульок, для легшої демонстрації роботи програми
                System.out.print("Зробіть свій вибір(1/2): ");
                int playerGuess = scanner.nextInt();

                if (playerGuess < 1 || playerGuess > 2) {
                    System.out.println("Ви повинні обрати число 1 або 2. Спробуйте ще раз." + System.lineSeparator());
                    continue;
                }

                pairOrNot = checkPairOrNot(playerGuess, computerHidden);

                if (pairOrNot) {
                    System.out.println("Ви вгадали!" + " Комп'ютер сховав " + computerHidden
                            + declension(computerHidden) + " Комп'ютер втрачає " + computerHidden
                            + declension(computerHidden) + "Ви отримуєте " + computerHidden
                            + declension(computerHidden));
                    playerStones += computerHidden;
                    computerStones -= computerHidden;
                    playerStones = keepLimitForPlayer(playerStones);
                    computerStones = keepLimitForComputer(computerStones);
                } else {
                    System.out.println("Ви не вгадали. " + " Комп'ютер сховав " + computerHidden
                            + declension(computerHidden) + " Комп'ютер отримує " + computerHidden
                            + declension(computerHidden) + "Ви втрачаєте "
                            + computerHidden + declension(computerHidden));
                    playerStones -= computerHidden;
                    computerStones += computerHidden;
                    playerStones = keepLimitForPlayer(playerStones);
                    computerStones = keepLimitForComputer(computerStones);
                }
                showBalance(playerStones, computerStones);
                playerTurn = false;
            } else {
                System.out.print("Комп'ютер відгадує, скільки камінців ви сховали." + System.lineSeparator()
                        + "Зробіть свій вибір(1-4): ");
                int playerHidden = scanner.nextInt();

                if (playerHidden > playerStones) {
                    System.out.println("Ви не можете сховати більше камінців ніж у вас є! Ваш баланс: " + playerStones
                            + System.lineSeparator());
                    continue;
                }

                int computerGuess = (int) (Math.random() * 2) + 1;

                if (playerHidden < 1 || playerHidden > 4) {
                    System.out.println("Ви повинні ввести число від 1 до 4. Спробуйте ще раз."
                            + System.lineSeparator());
                    continue;
                }

                pairOrNot = checkPairOrNot(computerGuess, playerHidden);

                if (pairOrNot) {
                    System.out.println("Комп'ютер вгадав! Ви втрачаєте " + playerHidden
                            + declension(playerHidden) + "Комп'ютер отримує " + playerHidden
                            + declension(playerHidden));
                    playerStones -= playerHidden;
                    computerStones += playerHidden;
                    playerStones = keepLimitForPlayer(playerStones);
                    computerStones = keepLimitForComputer(computerStones);
                } else {
                    System.out.println("Комп'ютер не вгадав. Ви отримуєте " + playerHidden
                            + declension(playerHidden) + "Комп'ютер втрачає " + playerHidden
                            + declension(playerHidden));
                    playerStones += playerHidden;
                    computerStones -= playerHidden;
                    playerStones = keepLimitForPlayer(playerStones);
                    computerStones = keepLimitForComputer(computerStones);
                }
                showBalance(playerStones, computerStones);
                playerTurn = true;
            }
            round++;
        }

        if (playerStones <= 0) {
            System.out.println("Ви програли! Комп'ютер переміг.");
            gameDuration(startTime);
        } else {
            System.out.println("Ви перемогли! Комп'ютер програв.");
            gameDuration(startTime);
        }
        repeatGameQuestion();
    }

    public static void greetings() { // метод з вітанням
        System.out.println("Ласкаво просиму в гру \"Stone Game\"! " + System.lineSeparator()
                + "Ви та комп'ютер маєте по 10 камінців, і по черзі повинні відгадувати, скільки камінців сховав "
                + "ваш опонент." + System.lineSeparator() + "За один хід можна заховати від 1 до 4 камінців. Поїхали!"
                + System.lineSeparator());
    }

    public static boolean checkPairOrNot(int guess, int hide) {
        if (guess == 1 && hide % 2 == 0) {
            return true;
        } else if (guess == 2 && !(hide % 2 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static String declension(int count) { // метод відмінювання слова "камінець" в залежності від кількості
        if (count == 1) {
            return " камінець. ";
        } else if (count == 0 || (count >= 5 && count <= 20)) {
            return " камінців. ";
        } else {
            return " камінця. ";
        }
    }

    public static int keepLimitForPlayer(int playerStones) { // метод утримання ліміту камінців для гравця
        return (playerStones < 0) ? 0 : (playerStones > 20) ? 20 : playerStones;
    }

    public static int keepLimitForComputer(int computerStones) { // метод утримання ліміту камінців для комп'ютера
        return (computerStones < 0) ? 0 : (computerStones > 20) ? 20 : computerStones;
    }

    public static void showBalance(int playerStones, int computerStones) { // метод для відображення балансу
        System.out.println("Ваш баланс: " + playerStones + declension(playerStones) + "Баланс комп'ютера: "
                + computerStones + declension(computerStones) + System.lineSeparator());
    }

    public static void repeatGameQuestion() { // метод з питанням про повторну гру
        Scanner scanner = new Scanner(System.in);
        System.out.println("Бажаєте зіграти ще раз? (1 - так, 2 - ні)");
        int playAgain = scanner.nextInt();
        if (playAgain == 1) {
            main(null);
        } else {
            System.out.println("Дякую за гру! До побачення.");
        }
    }

    public static void gameDuration(long startTime) { // метод відображення тривалості гри
        long endTime = System.currentTimeMillis();
        long durationMs = endTime - startTime;
        long minutes = (durationMs / 1000) / 60;
        long seconds = (durationMs / 1000) % 60;
        System.out.printf("Тривалість матчу: %d хвилин %d секунд\n", minutes, seconds);
    }
}
