# readme



# middle end 


aim : 

provide encapsultate information on request


how : 

Request(encapsulated JSON) can retrieve data from backend : Boolean/ User/ Rooms...
The middle end instantly provide the data in pending mode (encapsulate). 
When isLoaded() become true the data can be build by the class decorating the data 
(DownloadData).


# front end 


aim : 

display information and manage view


how :


1. with differents machines states : 

state of the application (AUTHENTIFICATION, WIFI, ...)


2. with a controler for an activity : 

observe activity event (click)
observe machine state (wifi_on => wifi_off)
and choose what operation execute on the view when event/state changing. 
the controler decide what's is the request to make with the current state and 
apply downloaded data to the view.




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




