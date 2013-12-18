import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner in = new Scanner(System.in);
    static String inputChar, secretWord;
    static StringBuffer accumulator = new StringBuffer();
    static int historyTrigger, undoCount = 0;
    static int livesCount = 6;
    static char rope, head, leftHand, rightHand, body, leftLeg, rightLeg  = ' ';
    static char arraySecrertWord[], arrayPrintSecretWord[], arrayHistory[];
    public static void main(String[] args) {
        secretWord();
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            checkLivesCount();
            drawField();
            System.out.println("\n");
            printSecretWord();
            System.out.println("\n");
            history();
            System.out.println();
            if (Arrays.equals(arraySecrertWord,arrayPrintSecretWord)) {     // ПРОВЕРЯЕМ ОТГАДАЛИ ЛИ МЫ СЛОВО
                System.out.println("Поздравляю, Вы победили!");
                break;
            }
            charInput();
            if (inputChar.equals("EXIT")) {                                 // команда завершения игры
                break;
            } else if (inputChar.equals("UNDO")) {
                if (undoCount == 0 && livesCount != 6) {
                    if (livesCount < 6) {
                        livesCount ++;
                        clearHangman();
                    }
                }
            } else if (inputChar.length() == 1) {
                if ( accumulator.indexOf(inputChar) == -1) {
                    accumulator.append(inputChar.charAt(0));
                    charInputControl();
                }
            }
        } while (livesCount > 0);
        if(livesCount==0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            checkLivesCount();
            drawField();
            System.out.println("\n");
            printSecretWord();
            System.out.println("\n");
            history();
            System.out.println("ПОТРАЧЕНО");
        }
        System.out.println("GAME OVER");
    }
    static void drawField() {
        System.out.println(" " + rope);
        System.out.println(" " + head);
        System.out.println(leftHand + " " + rightHand);
        System.out.println(" " + body);
        System.out.println(leftLeg + " " + rightLeg);
    }         // вывод в консоль человечка
    static void secretWord() {
        System.out.print("Загадайте слово:");
        if (in.hasNextLine()) {
            secretWord = in.nextLine();                             // ВВОДИМ СЕКРЕТНОЕ СЛОВО
            secretWord = secretWord.toUpperCase();                  // ДЕЛАЕМ ЗАГЛАВНЫЕ ЕГО ЗАГЛАВНЫМИ БУКВАМИ
            arraySecrertWord = new char[secretWord.length()];       //МАССИВ С ЗАГАДАНЫМ СЛОВОМ ДЛЯ СРАВНЕНИЯ
            arrayPrintSecretWord = new char[secretWord.length()];   //МАССИВ ДЛЯ ВЫВОДА НА ЭКРАН (ЗАШИФРОВАНЫЙ)
            for (int i = 0; i < arraySecrertWord.length; i++) {
                arraySecrertWord[i] = secretWord.charAt(i);         //ПРИ ПОМОЩИ ЦИКЛА ЗАПОЛНЯЕМ ЕГО БУКВАМИ
                if (secretWord.charAt(i) == '-') {                  //ЗАШИФРОВЫВАЕМ ВТОРОЙ МАССИВ
                    arrayPrintSecretWord[i] = '-';
                } else if (secretWord.charAt(i) == ' ') {
                    arrayPrintSecretWord[i] = ' ';
                } else {
                    arrayPrintSecretWord[i] = '_';
                }
            }
        } else {
            System.out.println("Допустимы только буквы");
        }
    }        // загадываем слово
    static void printSecretWord() {
        System.out.print("PoomsЫna: ");
        for (int i = 0; i < arrayPrintSecretWord.length; i++) {
            System.out.print(arrayPrintSecretWord[i] + " ");
        }
    }   // вывод в консоль зашифр. слова
    static void checkLivesCount() {
        if (livesCount <= 6) {
            leftLeg = '/';
            if (livesCount <= 5) {
                rightLeg = '\\';
                if (livesCount <= 4) {
                    body = '|';
                    if (livesCount <= 3) {
                        leftHand = '-';
                        if (livesCount <= 2) {
                            rightHand = '-';
                            if (livesCount <= 1) {
                                head = 'O';
                                if (livesCount == 0) {
                                    rope = '|';
                                }
                            }
                        }
                    }
                }
            }
        }
    }   // проверяем части тела
    static void charInput() {
        System.out.print("Arva>>>");
        if(in.hasNextLine()) {
            inputChar = in.nextLine();
            inputChar = inputChar.toUpperCase();
            historyTrigger++;
        } else {
            System.out.println("Вы ввели не букву!");
        }
    }         // ввод буквы для отгадывания
    static void charInputControl() {
        if(secretWord.indexOf(inputChar.charAt(0)) == -1) {
            livesCount--;
        } else {
            for (int i = 0; i < secretWord.length(); i++) {
                if (inputChar.charAt(0) == secretWord.charAt(i)) {
                    arrayPrintSecretWord[i] = inputChar.charAt(0);
                }
            }
        }
    }  // проверяет есть ли такая буква в загаданом слове и подменяет в шифре
    static void history() {
        if (historyTrigger == 0) {
            System.out.println("Ajalugu:");
            System.out.println("\n");
        } else {
            System.out.print("Ajalugu: ");
            arrayHistory = new char[accumulator.length()];
            for (int i = 0; i < arrayHistory.length; i ++) {
                arrayHistory[i] = accumulator.charAt(i);
                System.out.print(arrayHistory[i] + " ");
            }
            if (inputChar.length() != 1) {
                if (inputChar.equals("UNDO")) {
                    if (undoCount != 0) {
                        System.out.println("\n");
                        System.out.println("UNDO один раз уже использован");
                    } else {
                        undoCount ++;
                        System.out.println("\n");
                        System.out.println("Вы использовали UNDO");
                    }
                } else {
                    System.out.println("\n");
                    System.out.println("INPUT ERROR");
                }
            } else {
                if (secretWord.indexOf(inputChar.charAt(0)) == -1) {
                    System.out.println("\n");
                    System.out.println("Не угадал");
                } else {
                    System.out.println("\n");
                    System.out.println("Угадал");
                }
            }
        }
    }           // выводим наши действия с отгадыванием в консоль
    static void clearHangman () {
       rope = ' ';
       head = ' ';
       leftHand = ' ';
       rightHand = ' ';
       body = ' ';
       leftLeg = ' ';
       rightLeg  = ' ';
    }     // очищает поле для команды "UNDO"
}
