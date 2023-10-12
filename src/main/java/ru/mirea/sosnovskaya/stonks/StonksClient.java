package ru.mirea.sosnovskaya.stonks;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

public class StonksClient {
    public static void main(String[] args) throws SQLException {

//        Retrofit client = new Retrofit
//                .Builder()
//                .baseUrl("https://www.cbr.ru")
//                .addConverterFactory(JacksonConverterFactory.create(new XmlMapper()))
//                .build();
//        StonksService stonksService = client.create(StonksService.class);
//        try {
//            Response<DailyCurs> response = stonksService
//                    .getDailyCurs(LocalDate.of(2005, 06, 03)
//                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).execute();
//            DailyCurs dailyCurs = response.body();
//            Optional<Valute> maxValute = dailyCurs.getValutes().stream()
//                    .filter(valute -> !valute.getName().equals("СДР (специальные права заимствования)"))
//                    .max(Comparator.comparingDouble(Valute::getValue));
//            DatabaseService databaseService = new DatabaseServiceImpl();
//            if (maxValute.isPresent()) {
//                System.out.println(maxValute.get());
//                Valute mv = maxValute.get();
//                System.out.println("УСПЕШНОЕ СОХРАНЕНИЕ");
//                databaseService.saveMaxValuteOfDate("SosnovskayaVE", mv, LocalDate.of(2005, 06, 03));
//            }
//            Valute valute = databaseService.getValuteOfDate(LocalDate.of(2005, 06, 03));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

        Retrofit client = new Retrofit
                .Builder()
                .baseUrl("https://www.cbr.ru")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();
        StonksService stonksService = client.create(StonksService.class);
        try {
            Response<DailyCurs> response = stonksService
                        .getDailyCurs(LocalDate.of(2005, 06, 03)
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).execute();
                DailyCurs dailyCurs = response.body();
                Optional<Valute> maxValute = dailyCurs.getValutes().stream()
                        .filter(valute -> !valute.getName().equals("СДР (специальные права заимствования)"))
                        .max(Comparator.comparingDouble(Valute::getValue));
                DatabaseService databaseService = new DatabaseServiceImpl();
                if (maxValute.isPresent()) {
                    System.out.println(maxValute.get());
                    Valute mv = maxValute.get();
                    System.out.println("УСПЕШНОЕ СОХРАНЕНИЕ");
                databaseService.saveMaxValuteOfDate("SosnovskayaVE", mv, LocalDate.of(2005, 06, 03));
            }
            Valute valute = databaseService.getValuteOfDate(LocalDate.of(2005, 06, 03));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
