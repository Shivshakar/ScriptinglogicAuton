package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {

    public static String convertCountry(String countryCode)
    {
            String convertedCountry = "";

            switch (countryCode)
            {
                case "IN": convertedCountry = "India"; break;
                case "US": convertedCountry = "United States"; break;
                case "CA": convertedCountry = "Canada"; break;
                case "GB": convertedCountry = "United Kingdom"; break;
                case "AU": convertedCountry = "Australia"; break;
                case "DE": convertedCountry = "Germany"; break;
                case "FR": convertedCountry = "France"; break;
                case "JP": convertedCountry = "Japan"; break;
                case "CN": convertedCountry = "China"; break;
                case "BR": convertedCountry = "Brazil"; break;
                case "ZA": convertedCountry = "South Africa"; break;
                case "RU": convertedCountry = "Russia"; break;
                case "IT": convertedCountry = "Italy"; break;
                case "ES": convertedCountry = "Spain"; break;
                case "MX": convertedCountry = "Mexico"; break;
                case "KR": convertedCountry = "South Korea"; break;
                case "NP": convertedCountry = "Nepal"; break;
                default: convertedCountry = "Unknown"; break;
            }


            return convertedCountry;
    }

    public static String getGender(String genderID)
    {
        String gender = "";

        switch (genderID)
        {
            case "0" : gender ="Male";break;
            case "1" : gender ="Female";break;
            case "2" : gender ="Other";break;
        }

        return gender;

    }


    public static String convertDate(String dbDateStr) throws ParseException // 1985-09-05 -> yyyy-MM-dd
    {
         String convertedDate = "";

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
         Date date = sd.parse(dbDateStr);  // convert to string -> dd/MM/yyyy

        SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy"); // 05/09/1985
        convertedDate = sd2.format(date);

       return convertedDate;


       /* Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dbDateStr);

        return new SimpleDateFormat("dd/MM/yyyy").format(date);*/

    }
}
