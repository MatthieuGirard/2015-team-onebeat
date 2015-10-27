# readme



# middle end 


the aim of the package is to provide encapsulated information on request.
A Request(encapsulated JSON) can retrieve data from backend : Boolean/ User/ Rooms/...
in pending mode (encapsulated in PendingData<T>).
The class asking for pendingData need to wait until isLoaded() become true
to extract the data T from the PendingData<T> instance.
(PendingData).


# front end 


the aim is to display information and manage view.
Many static BoardState give information about the current state of an app :
activity/authentification...
When an event occurs on the view the activity has just to update the current
state on one of the static boardState.
it's possible for an observer to subscribe to a boardState, and get notified
when the currentState has changed.
It's the case of the controler that encapsulate an activity and when the board
state change his currentState get notified, make request for pendingData,
wait for data loaded and parametrize the view.

exemple :

BOARDState.AUTHENTIFICATION enter state : TryConnect  =>  notifyObserver();

ControlerMainActivity : isNotified

=> display a loading component on the main activity

=> get boolean

=> if(boolean == false) not connected : retry ?  else :

=> undisplay the loading component on the main activity

=> BOARDState.Display enter state : MY_ROOM_ACTIVITY





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




