package com.zyjk.posmall.bean;

/**
 * Created by ${GodofSwond} on 2018/5/8.
 */

public class Subjects {

    private Ratings rating;

    public Ratings getRating() {
        return rating;
    }

    public void setRating(Ratings rating) {
        this.rating = rating;
    }

    public static class Ratings {

        private int max;
        private double aveprrage;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAveprrage() {
            return aveprrage;
        }

        public void setAveprrage(double aveprrage) {
            this.aveprrage = aveprrage;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }
}
