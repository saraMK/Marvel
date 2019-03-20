package com.marvel.marvel.Models;

import com.marvel.marvel.Utiles.GeneralStrings;

import java.util.HashMap;

import static com.marvel.marvel.Utiles.GeneralStrings.API_KEY;
import static com.marvel.marvel.Utiles.GeneralStrings.HASH;
import static com.marvel.marvel.Utiles.GeneralStrings.LIMIT;
import static com.marvel.marvel.Utiles.GeneralStrings.TIME_STAMP;

public class QueriesModel {
    HashMap<String, String> map = new HashMap<>();

    public QueriesModel() {
        map.put(Keys.ts.name(), TIME_STAMP);
        map.put(Keys.apikey.name(), API_KEY);
        map.put(Keys.hash.name(), HASH);
        map.put(Keys.limit.name(), LIMIT);

    }

    public void setIndex(int index) {
        map.put(Keys.offset.name(), index + "");
    }

    public void setSearchByName(String text) {
        map.put(Keys.nameStartsWith.name(), text);
    }

    enum Keys {
        ts,
        apikey,
        hash,
        limit,
        offset, nameStartsWith
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
