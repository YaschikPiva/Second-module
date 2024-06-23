import java.util.ArrayList;
import java.util.Arrays;

public class YearlyReport {
    ArrayList<String[]> yearlyReport;
    YearlyReport() {
        yearlyReport = new ArrayList<>();
    }
    void readYearlyReport() {
        ArrayList<String> Lines = new ArrayList<>();
        FileReader fileReader = new FileReader();
        Lines = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < Lines.size(); i++) { //Вытаскиваем строки с операциями без названия столбцов
            String line = Lines.get(i);
            String[] lineContents = line.split(","); //Переделываем строки в массивы
            yearlyReport.add(lineContents); //Наполняем новый список форматированными строками
            System.out.println(Arrays.toString(yearlyReport.get(i - 1))); //Печать списка
        }
    }

    ArrayList calculateYearProfit(ArrayList<Integer> yearProfit) { //Считаем доходы по месяцам
        int monthProfit = 0; //Вводим параметр дохода за месяц
            for (String[] lineContents : yearlyReport) { //Извлекаем позиции
                boolean prof = Boolean.parseBoolean(lineContents[2]);
                if (!prof) {
                    monthProfit = Integer.parseInt(lineContents[1]); //Добавляем значение цены позиции, умноженное на количество
                    yearProfit.add(monthProfit); //Вносим прибыль за месяц в массив прибылей за год
                }
            }
        return yearProfit;
    }

    ArrayList calculateYearExpensive(ArrayList<Integer> yearExpensive) { //Считаем доходы по месяцам
        int monthExpensive = 0; //Вводим параметр дохода за месяц
        for (String[] lineContents : yearlyReport) { //Извлекаем позиции
            boolean exp = Boolean.parseBoolean(lineContents[2]);
            if (exp) {
                monthExpensive = Integer.parseInt(lineContents[1]); //Добавляем значение цены позиции, умноженное на количество
                yearExpensive.add(monthExpensive); //Вносим прибыль за месяц в массив прибылей за год
            }
        }
        return yearExpensive;
    }

    public static void yearStatistic(ArrayList<Integer> yearProfit, ArrayList<Integer> yearExpensive) {
        int allMonthsProfit = 0;
        int allMonthExpensive = 0;
        System.out.println("Год 2021");
        System.out.println("Доходы за каждый месяц:");
        for (int i = 0; i < yearProfit.size(); i++){
            allMonthsProfit += yearProfit.get(i);
            if (i == 0) {
                System.out.println("Январь " + yearProfit.get(i));
            } else if (i == 1) {
                System.out.println("Февраль " + yearProfit.get(i));
            } else {
                System.out.println("Март " + yearProfit.get(i));
            }
        }
        for (Integer Expensive : yearExpensive) {
            allMonthExpensive += Expensive;
        }
        System.out.println("Средний расход за весь год: " + (allMonthExpensive/yearExpensive.size()));
        System.out.println("Средний доход за год: " + (allMonthsProfit/yearProfit.size()));
        System.out.println();
    }
}
