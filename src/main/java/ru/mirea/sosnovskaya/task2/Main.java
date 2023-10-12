package ru.mirea.sosnovskaya.task2;


import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        Retrofit client = new Retrofit
                .Builder()
                .baseUrl("https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();

        Serv service = client.create(Serv.class);
        List<DTO> films = service.getDTO().execute().body();

        int maxCastLen = -1;
        DTO maxCastFilm = new DTO();
        for (DTO film : films) {
            if(film.getYear() < 2000 && film.getCast().size() > maxCastLen) {
                maxCastLen = film.getCast().size();
                maxCastFilm = film;
            }
        }
        if(maxCastFilm == null) {
            System.out.println("Не найдено ни одного фильма");
        }
        else {
            System.out.println(String.format("Самый кастовый фильм до 2000 года: %s (%s актёра)",
                    maxCastFilm.getTitle(), maxCastLen));
            maxCastFilm.getDescriptionFilm();
        }
    }
}
