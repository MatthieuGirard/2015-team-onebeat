# readme



# middle end 


aim : 

provide encapsultate information on request


how : 

when request(encapsulated JSON) is asked to the middle end ,
the middle end instantly provide Download data<T>: 
it's an instance of the downloaded data T requested. 
when isLoaded() become true the downloaded data of T can provide
access to the information.


# front end 


aim : 

display information and manage view


how :


with differents machines states : 

state of the application (AUTHENTIFICATION, WIFI, ...)


with differents controler : 

observe activity event (click)
observe machine state (wifi_on => wifi_off)
and choose what operation execute on the view. 
the controler decide what's is the request to make with
the current state and apply downloaded data to the view.




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




