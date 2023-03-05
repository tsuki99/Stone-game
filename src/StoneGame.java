import java.util.Scanner;
import java.util.Random;

public class StoneGame {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int playerStones = 10;
        int computerStones = 10;
        int round = 1;
        boolean playerTurn = true;

        System.out.println("Добро пожаловать в игру \"Stone Game\"!");
        System.out.println("Вы и компьютер имеете по 10 камней, и по очереди должны угадывать, сколько камней противник спрятал.");
        System.out.println("За один ход можно спрятать от 1 до 4 камней. Поехали!\n");

        while (playerStones > 0 && computerStones > 0) {
            System.out.println("Раунд " + round + ":");

            if (playerTurn) {
                System.out.println("Вы угадываете, сколько камней спрятал компьютер.");
                System.out.println("Угадывайте: ");
                int computerHidden = random.nextInt(Math.min(computerStones, 4)) + 1; // условие для компьютера, чтобы он не ставил больше чем у него есть, и не больше 4-х камней
                int playerGuess = scanner.nextInt();

                if (playerGuess < 1 || playerGuess > 4) {
                    System.out.println("Вы должны ввести число от 1 до 4. Попробуйте снова.");
                    continue;
                }

                if (playerGuess == computerHidden) {
                    System.out.println("Вы угадали!" + " Компьютер спрятал " + computerHidden + " камня. " + " Компьютер теряет " + computerHidden + " камня. Вы получаете " + computerHidden + " камня.\n");
                    playerStones += computerHidden;
                    computerStones -= computerHidden;
                } else {
                    System.out.println("Вы не угадали. " + " Компьютер спрятал " + computerHidden + " камня. " + " Компьютер получает " + computerHidden + " камня. Вы теряете " + computerHidden + " камня.\n");
                    playerStones -= computerHidden;
                    computerStones += computerHidden;
                }

                System.out.println("---------------------------------------------");
                System.out.println("Ваш баланс: " + playerStones + " камней. Баланс компьютера: " + computerStones + " камней.");
                System.out.println("---------------------------------------------\n");
                playerTurn = false;

            } else {
                System.out.println("Компьютер угадывает, сколько камней вы спрятали.");
                System.out.println("Прячьте: ");
                int playerHidden = scanner.nextInt();
                int computerGuess = (int) (Math.random() * 4) + 1;

                if (playerHidden < 1 || playerHidden > 4) { // условие, чтобы я не мог поставить больше камней чем 4
                    System.out.println("Вы должны спрятать от 1 до 4 камней. Попробуйте снова.");
                    continue;
                }
                if (playerHidden > playerStones) { // условие, чтобы я не мог поставить больше камней, чем есть в наличии
                    System.out.println("Вы не можете спрятать больше камней, чем есть в наличии. Попробуйте снова.");
                    continue;
                }

                if (playerHidden == computerGuess) {
                    System.out.println("Компьютер угадал!" + " Вы спрятали " + playerHidden + " камня. " + " Вы теряете " + playerHidden + " камня. Компьютер получает " + playerHidden + " камня.\n");
                    playerStones -= playerHidden;
                    computerStones += playerHidden;
                } else {
                    System.out.println("Компьютер не угадал." + " Вы спрятали " + playerHidden + " камня. " + " Вы получаете " + playerHidden + " камня. Компьютер теряет " + playerHidden + " камня.\n");
                    playerStones += playerHidden;
                    computerStones -= playerHidden;
                }

                System.out.println("---------------------------------------------");
                System.out.println("Ваш баланс: " + playerStones + " камней. Баланс компьютера: " + computerStones + " камней.");
                System.out.println("---------------------------------------------\n");
                playerTurn = true;
            }

            round++;
        }

        if (playerStones == 0) {
            System.out.println("Вы проиграли! У вас больше нет камней.");
            long endTime = System.currentTimeMillis();
            long durationMs = endTime - startTime;
            long minutes = (durationMs / 1000) / 60;
            long seconds = (durationMs / 1000) % 60;
            System.out.printf("Длительность матча: %d минут %d секунд\n", minutes, seconds);
        } else {
            System.out.println("Поздравляем, вы победили! Компьютер проиграл все свои камни.");
            long endTime = System.currentTimeMillis();
            long durationMs = endTime - startTime;
            long minutes = (durationMs / 1000) / 60;
            long seconds = (durationMs / 1000) % 60;
            System.out.printf("Длительность матча: %d минут %d секунд\n", minutes, seconds);
        }

        System.out.println("----------------------------------------");
        System.out.println("Хотите сыграть ещё раз? (1 - да, 2 - нет)");
        int playAgain = scanner.nextInt();
        if (playAgain == 1) {
            main(null);
        } else {
            System.out.println("Спасибо за игру! До свидания.");
        }

    }
}