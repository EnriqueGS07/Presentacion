package main.game.services;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.game.model.Player;
import main.game.persistence.PlayerPersistence;

@Service
public class PlayerServices {
    @Autowired 
    private PlayerPersistence pp = null;

    public void setPangeaPersistence(PlayerPersistence pp){
        this.pp = pp;
    }
    
    public boolean allReady(){
        return pp.allReady();
    }

    public void addNewPlayer(Player player){
        pp.addPlayer(player);
    }

    public void addOneSol(Player player){
        synchronized(player){
            pp.addSoldier(player);
        }
    }

    public ArrayList<Player> getAllPlayers(){
        return pp.getAllPlayers();
    }

    public Player getPlayer(String nickname){
        return pp.getPlayer(nickname);
    }
    
    public void substractSoldiers(String nickname, int subsoldiers, String tipo){
        pp.substractSoldiers(nickname, subsoldiers, tipo);
    }

    public Set<String> getNations(Player player){
        return pp.getNacionesPlayer(player);
    }

    public void addNacion(String idNacion, String nickname){
        pp.addNacion(idNacion, nickname);
    }

    public void removeNation(String idNation, String nickname){
        pp.removeNation(idNation, nickname);
    }

    public void deleteAll(){
        pp.deleteAll();
    }
}
