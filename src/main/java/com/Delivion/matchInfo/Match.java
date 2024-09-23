package com.Delivion.matchInfo;

public class Match {
    private String location;
    private String scheduled;
    private Country country;
    private String last_changed;
    private String status;
    private int fixture_id;
    private Team home;
    private Team away;
    private int id;
    private Object federation;
    private Odds odds;
    private String time;
    private String added;
    private Competition competition;
    private Outcomes outcomes;
    private Scores scores;
    private Urls urls;

    public static class Country {
        private String name;
        private int id;
        private String uefa_code;
        private String fifa_code;
        private boolean is_real;

        public String getName() {
            return name;
        }
    }

    public static class Team {
        private int id;
        private String name;
        private int country_id;
        private String stadium;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCountry_id() {
            return country_id;
        }

        public String getStadium() {
            return stadium;
        }
    }

    public static class Odds {
        private Live live;
        private Pre pre;

        public static class Live {
            private Double one;
            private Double two;
            private Double x;
        }

        public static class Pre {
            private double one;
            private double two;
            private double x;
        }
    }

    public static class Competition {
        private boolean is_cup;
        private boolean active;
        private boolean has_groups;
        private boolean national_teams_only;
        private int tier;
        private String name;
        private boolean is_league;
        private int id;
    }

    public static class Outcomes {
        private String half_time;
        private String full_time;
        private Object extra_time;
        private Object penalty_shootout;
    }

    public static class Scores {
        private String score;
        private String ht_score;
        private String ft_score;
        private String et_score;
        private String ps_score;

        public String getScore() {
            return score;
        }
    }

    public static class Urls {
        private String events;
        private String statistics;
        private String lineups;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public String getLocation() {
        return location;
    }

    public Country getCountry() {
        return country;
    }

    public Scores getScores() {
        return scores;
    }

    public String getScheduled() {
        return scheduled;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}