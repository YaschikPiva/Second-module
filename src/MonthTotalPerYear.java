import java.util.ArrayList;

public class MonthTotalPerYear {
    public static void checkMonthsPerYear(ArrayList<Integer> monthsProfit,
                                          ArrayList<Integer> monthsExpensive,
                                          ArrayList<Integer> yearProfit,
                                          ArrayList<Integer> yearExpensive) {
        for (int i = 0; i < monthsProfit.size(); i++) {
            int monthProf = monthsProfit.get(i);
            int monthExp = monthsExpensive.get(i);
            int yearProf = yearProfit.get(i);
            int yearExp = yearExpensive.get(i);
            if (monthProf != yearProf || monthExp != yearExp) {
                System.out.println("Расхождение в месяце " + (i + 1));
            }
        }
        System.out.println("Отчёты сверены.\n" +
                "Да, мне было страшно, но я это сделал.");
    }
}
