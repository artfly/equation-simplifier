package com.github.artfly.parser.model;

public enum Operation {
    ADD() {
        @Override
        public String value() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        public String value() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public String value() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        public String value() {
            return "/";
        }
    },
    POW {
        @Override
        public String value() {
            return "^";
        }
    };

    public abstract String value();
}