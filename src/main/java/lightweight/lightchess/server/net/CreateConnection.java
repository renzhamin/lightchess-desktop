/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightweight.lightchess.server.net;


import lightweight.lightchess.net.Information;
import lightweight.lightchess.net.NetworkConnection;

import java.util.HashMap;


public class CreateConnection implements Runnable{
    
    HashMap<String, Information> clientList;
    HashMap<String, Information> loggedInList;
    NetworkConnection nc;
    public CreateConnection(HashMap<String,Information> cList,HashMap<String,Information> loggedInList, NetworkConnection nConnection){
        clientList=cList;
        this.loggedInList = loggedInList;
        nc=nConnection;    
    }
        
    
    @Override
    public void run() {
        Object userObj=nc.read();
        String username=(String)userObj;
        
        System.out.println("User : "+username+" connected");
        
        clientList.put(username,new Information(username,nc));
        System.out.println("HashMap updated"+clientList);
        new Thread(new ReaderWriterServer(username,nc,clientList,loggedInList)).start();
        
    }
    
}
