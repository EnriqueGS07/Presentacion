package main.game.model;

import java.util.ArrayList;

public abstract class  Poder {
        private String name;
        
        protected static String active = "TripleClick";
        protected ArrayList<String> activePowerPlayers = new ArrayList<String>();

        public Poder(String name){
                this.name = name;
        }

        public String getName(){
                return this.name;
        }

        public static String getActive(){
                return active;
        };

        public void activatePower(ArrayList<String> players){
        };
        
        public void activatePower(String  players){
        };

        public ArrayList<String> getActivePlayers(){
                return activePowerPlayers;
        }

        public void deactivatePower(){
                this.activePowerPlayers = new ArrayList<String>();
                this.active = "";
        };

}
