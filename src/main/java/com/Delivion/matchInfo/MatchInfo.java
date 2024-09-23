package com.Delivion.matchInfo;

import java.util.List;

public class MatchInfo {
    private boolean success;
    private Data data;

    public static class Data {
        private List<Match> match;



        public List<Match> getMatch() {
            return match;
        }
    }

    public Data getData() {
        return data;
    }
}