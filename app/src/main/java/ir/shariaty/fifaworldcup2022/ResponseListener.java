package ir.shariaty.fifaworldcup2022;

import ir.shariaty.fifaworldcup2022.models.FixtureResponse;

public interface ResponseListener {
    void didFetch(FixtureResponse response, String message);
    void didError(String message);
}
