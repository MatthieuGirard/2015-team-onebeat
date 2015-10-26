# readme



# middle end 


aim : 

provide encapsulate information on request


how : 

Request(encapsulated JSON) can retrieve data from backend : Boolean/ User/ Rooms...

The middle end instantly provide the data in pending mode (encapsulate). 

When isLoaded() become true the data can be build by the class decorating the data 
(DownloadData).


# front end 


aim : 

display information and manage view


how :


__I with differents machines states : 

state of the application (AUTHENTIFICATION, WIFI, ...)
The view can modify the state of one/some machine state
(when click event on button => AUTENTIFICATION.setState(TryConnect) )


__II with a controler for an activity :

the controler only observe machine state, when a stateis reached
it look if it has work to do :

AUTHENTIFICATION(machine state) enter state : TryConnect

=> get boolean for success auth

=> display a loading component on the managed activity



# fast understanding read line 


PACKAGE : MIDDLE END 

1 Util : signature, MacAddrSignature, Device

2 RetrieveData : BooleanData

3 Network : DownloadData 

4 RetrieveData : RoomData 

5 Network : Request, BackendTask

PACKAGE : FRONT END 

6 MachineState : Authentification 

7 Util : Controler




