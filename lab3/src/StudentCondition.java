public enum StudentCondition {
    odrabiający{
        @Override
            public String toString(){
            return "odrabiający";
        }
    },
    chory{
        @Override
            public String toString(){
            return "chory";
        }
    },
    nieobecny{
        @Override
        public String toString(){
            return "nieobecny";
        }
    },
    obecny{
        @Override
        public String toString(){
            return "obecny";
        }
    }
}
