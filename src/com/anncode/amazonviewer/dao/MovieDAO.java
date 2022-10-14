package com.anncode.amazonviewer.dao;

import static com.anncode.amazonviewer.db.DataBase.*;
import com.anncode.amazonviewer.db.IDBConnection;
import com.anncode.amazonviewer.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public interface MovieDAO extends IDBConnection {

    default Movie setMovieViewed(Movie movie){
        return movie;
    }

    default ArrayList<Movie> read(){
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection connection = connectToDB()){
            String query = "SELECT * FROM" + TMOVIE;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getString(TMOVIE_TITLE),
                        rs.getString(TMOVIE_GENRE),
                        rs.getString(TMOVIE_CREATOR),
                        Integer.valueOf(rs.getString(TMOVIE_DURATION)),
                        Short.valueOf(rs.getString(TMOVIE_YEAR)));
                movie.setId(Integer.valueOf(rs.getString(TMOVIE_ID))); // Estaba en getInt pero lo cambie a getString

                movies.add(movie);

            }
        }catch (SQLException e){

        }

        return movies;
    }
  // debia poner private, pero da error (y)
    static boolean getMovieViewed() {
        return  false;
    }
}
