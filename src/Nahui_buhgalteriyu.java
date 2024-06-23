import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Nahui_buhgalteriyu {
    public static void main(String[] args) {
        MonthlyReport monthReport = new MonthlyReport();
        YearlyReport yearReport = new YearlyReport();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> monthsProfit = new ArrayList<>();
        ArrayList<Integer> monthsExpensive = new ArrayList<>();
        ArrayList<Integer> yearProfit = new ArrayList<>();
        ArrayList<Integer> yearExpensive = new ArrayList<>();

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            if (command.equals("1")) { //Считывание месячных отчётов
                monthReport.readMonthlyReports();
            } else if (command.equals("2")) { //считывание годового отчёта
                yearReport.readYearlyReport();
                System.out.println(yearReport);
            } else if (command.equals("3")) { //Сравнение годового и месячных отчётов по доходам/расходам
                if (monthReport.monthReports.get(1) == null || yearReport.yearlyReport.size() == 0) {
                    System.out.println("Отчёты не считаны.");
                } else {
                    monthReport.calculateMonthsProfit(monthsProfit);
                    //System.out.println(monthsProfit);
                    monthReport.calculateMonthsExpensive(monthsExpensive);
                    //System.out.println(monthsExpensive);
                    yearReport.calculateYearProfit(yearProfit);
                    //System.out.println(yearProfit);
                    yearReport.calculateYearExpensive(yearExpensive);
                    //System.out.println(yearExpensive);
                    MonthTotalPerYear.checkMonthsPerYear(monthsProfit, monthsExpensive, yearProfit, yearExpensive);
                }
            } else if (command.equals("4")) { //Сводка по месячным отчётам
                if (monthReport.monthReports.get(1) == null) {
                    System.out.println("Отчёты не считаны.");
                } else {
                    monthReport.monthlyBestPositionReport();
                    System.out.println("У меня вино дороже стоит!");
                }
            } else if (command.equals("5")) { //Сводка по годовому отчёту
                if (yearReport.yearlyReport.size() == 0) {
                    System.out.println("Отчёты не считаны.");
                } else {
                    YearlyReport.yearStatistic(yearProfit, yearExpensive);
                    System.out.println("Я был бухой, когда отвечал");
                }
            } else if (command.equals("Fluggegecheimen")) {
                System.out.println("Не могу осуждать...\n"+
                        "Хотя осуждаю!\n"+
                        "Внести Флюггегехаймен!");
                break;
            } else {
                System.out.println("Сомнительно... но ОКЭЙ!");
            }
        }
    }

    static void printMenu() { //Печать меню
        System.out.println();
        System.out.println("Выберите пункт меню\n"+
                "1 - Считать все месячные отчёты\n"+
                "2 - Считать годовой отчёт\n"+
                "3 - Сверить отчёты\n"+
                "4 - Вывести информацию обо всех месячных отчётах\n"+
                "5 - Вывести информацию о годовом отчёте\n"+
                "Стоп-слово - Fluggegecheimen");
    }
}
