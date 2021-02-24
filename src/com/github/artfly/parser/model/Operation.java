package com.github.artfly.parser.model;

public enum Operation {
    ADD {
        @Override
        public String value() {
            return "+";
        }

        @Override
        public int apply(int x, int y) {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        public String value() {
            return "-";
        }

        @Override
        public int apply(int x, int y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        public String value() {
            return "*";
        }

        @Override
        public int apply(int x, int y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public String value() {
            return "/";
        }

        @Override
        public int apply(int x, int y) {
            return x / y;
        }
    },
    POW {
        @Override
        public String value() {
            return "^";
        }

        @Override
        public int apply(int x, int y) {
            return (int) Math.pow(x, y);
        }
    };

    public abstract String value();

    public abstract int apply(int x, int y);
}