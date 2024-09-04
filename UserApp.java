import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные через пробел (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        try {
            // Проверка количества введенных данных
            if (data.length != 6) {
                throw new IllegalArgumentException("Ошибка: введено " + (data.length < 6 ? "меньше" : "больше") + " данных, чем требуется.");
            }

            // Распознавание и проверка данных
            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String dateOfBirth = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            // Проверка формата даты
            if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", dateOfBirth)) {
                throw new IllegalArgumentException("Ошибка: неверный формат даты рождения. Ожидается формат dd.mm.yyyy.");
            }

            // Проверка формата номера телефона (целое беззнаковое число)
            if (!Pattern.matches("\\d+", phoneNumber)) {
                throw new IllegalArgumentException("Ошибка: номер телефона должен быть целым беззнаковым числом.");
            }

            // Проверка формата пола (f или m)
            if (!gender.equals("f") && !gender.equals("m")) {
                throw new IllegalArgumentException("Ошибка: пол должен быть обозначен символом 'f' или 'm'.");
            }

            // Запись данных в файл
            writeToFile(surname, name, patronymic, dateOfBirth, phoneNumber, gender);
            System.out.println("Данные успешно записаны в файл.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом:");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void writeToFile(String surname, String name, String patronymic, String dateOfBirth, String phoneNumber, String gender) throws IOException {
        String fileName = surname + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) { // true - добавление в конец файла
            writer.write(surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender + "\n");
        }
    }
}
