import java.time.LocalDate;

public class test {

    public static void main(String[] args) {
        LocalDate ld1=LocalDate.of(2022,9,17);
        boolean isWeekend1 = isWeekEnd(ld1);
        System.out.println("17 Sep 2022 isWeekend: "+isWeekend1);

        LocalDate ld2=LocalDate.of(2022,9,19);
        boolean isWeekend2 = isWeekEnd(ld2);
        System.out.println("19 Sep 2022 isWeekend: "+isWeekend2);
    }

    /*Function to check if given date is weekend or not*/
    public static boolean isWeekEnd(LocalDate localDate)
    {
        String dayOfWeek = localDate.getDayOfWeek().toString();
        if("SATURDAY".equalsIgnoreCase(dayOfWeek)||
                "SUNDAY".equalsIgnoreCase(dayOfWeek))
        {
            return true;
        }
        return false;
    }
}
