import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<Integer, ArrayList<String[]>> monthlyReports;
    HashMap<Integer, ArrayList<String>> monthReports = new HashMap<>();
    MonthlyReport() {
        monthlyReports = new HashMap<>();
    }

    void readMonthlyReports() {
        ArrayList<String> monthReport = new ArrayList<>();
        FileReader fileReader = new FileReader();
        for (int i = 1; i < 4; i++) {
            ArrayList<String> Lines = fileReader.readFileContents("m.20210" + i + ".csv");
            monthReports.put(i, Lines); //Создаём отчёт по всем месяцам
        }
        /*System.out.println(monthReports.get(1));
        System.out.println(monthReports.get(2));
        System.out.println(monthReports.get(3));*/
        for (int i = 1; i <= 3; i++) {
            monthReport = monthReports.get(i); //Вытаскиваем месяцы из общего отчёта
            ArrayList<String[]> splitMonth = new ArrayList<>();
            for (int j = 1; j < monthReport.size(); j++) { //Вытаскиваем строки с операциями без названия столбцов
                String line = monthReport.get(j);
                String[] lineContents = line.split(","); //Переделываем строки в массивы
                splitMonth.add(lineContents); //Наполняем новый список форматированными строками
                System.out.println(Arrays.toString(splitMonth.get(j - 1))); //Печать списка
            }
            monthlyReports.put(i, splitMonth); //Наполняем новый хэш отформатированными отчётами
            System.out.println();
        }
    }

    ArrayList calculateMonthsProfit(ArrayList<Integer> monthsProfit) { //Считаем доходы по месяцам
        for (ArrayList<String[]> splitMonth : monthlyReports.values()) { //Извлекаем месяцы из отчёта
            int monthProfit = 0; //Вводим параметр дохода за месяц
            for (String[] lineContents : splitMonth) { //Извлекаем позиции
                boolean prof = Boolean.parseBoolean(lineContents[1]); //Параметр доход
                if (!prof) {
                  monthProfit += Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]); //Добавляем значение цены позиции, умноженное на количество
                }
            }
            monthsProfit.add(monthProfit); //Вносим прибыль за месяц в массив прибылей за все месяцы
        }
        return monthsProfit;
    }

    ArrayList calculateMonthsExpensive(ArrayList<Integer> monthsExpensive) { //Считаем расходы по месяцам
        for (ArrayList<String[]> splitMonth : monthlyReports.values()) { //Извлекаем месяцы из отчёта
            int monthExpensive = 0;
            for (String[] lineContents : splitMonth) { //Извлекаем позиции
                boolean exp = Boolean.parseBoolean(lineContents[1]); //Параметр расход
                if (exp) {
                    monthExpensive += Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]);
                }
            }
            monthsExpensive.add(monthExpensive);
        }
        return monthsExpensive;
    }

    void monthlyBestPositionReport() { //Выводим отчёт за каждый месяц
        for (int j = 1; j <= 3; j++) { //Извлекаем месяцы из отчёта
            ArrayList<String[]> splitMonth = monthlyReports.get(j);
            if (j == 1) {
                System.out.println("Январь");
            } else if (j == 2) {
                System.out.println("Февраль");
            } else {
                System.out.println("Март");
            }
            int profPositionCost = 0; //Вводим параметр дохода за лучшую позицию
            String profPositionName = ""; //Вводим параметр имени лучшей доходной позиции
            int expPositionCost = 0; //Вводим параметр дохода за лучшую позицию
            String expPositionName = ""; //Вводим параметр имени лучшей доходной позиции
            for (String[] lineContents : splitMonth) { //Извлекаем позиции
                boolean exp = Boolean.parseBoolean(lineContents[1]); //Параметр расход
                if (exp) {
                    int positionCost = Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]); //Считаем значение цены позиции, умноженное на количество
                    if (expPositionCost < positionCost) {
                        expPositionCost = positionCost;
                        expPositionName = lineContents[0];
                    }
                } else {
                    int positionCost = Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]); //Считаем значение цены позиции, умноженное на количество
                    if (profPositionCost < positionCost) {
                        profPositionCost = positionCost;
                        profPositionName = lineContents[0];
                    }
                }
            }
            System.out.println("Самый прибыльный товар: " + profPositionName + ". Стоимость: " + profPositionCost);
            System.out.println("Самая большая трата: " + expPositionName + ". Стоимость: " + expPositionCost);
            System.out.println();
        }
    }
}
